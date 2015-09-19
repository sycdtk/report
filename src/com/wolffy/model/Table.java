package com.wolffy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 数据表结构
 * @author wolffy
 *
 */
public class Table {
	
	public static String AESC = "aesc";
	public static String DESC = "desc";
	
	
	private Map<Integer, Map<Integer, String>> tableMap = new HashMap<Integer, Map<Integer, String>>();
	
	/**
	 * 表格增加数据
	 * @param rowNum
	 * @param columnNum
	 * @param content
	 */
	public void put(int rowNum, int columnNum, String content){
		if(tableMap.containsKey(rowNum)){
			tableMap.get(rowNum).put(columnNum, content);
		}else{
			Map<Integer, String> recordMap = new HashMap<Integer, String>();
			recordMap.put(columnNum, content);
			tableMap.put(rowNum, recordMap);
		}
	}
	
	/**
	 * 获取表格数据
	 * @param rowNum
	 * @param columnNum
	 * @return
	 */
	public String get(int rowNum, int columnNum){
		String result = null;
		if(tableMap.containsKey(rowNum)){
			if(tableMap.get(rowNum).containsKey(columnNum)){
				result = tableMap.get(rowNum).get(columnNum);
			}
		}
		return result;
	}
	
	/**
	 * 清除某个表格数据
	 * @param rowNum
	 * @param columnNum
	 */
	public void clear(int rowNum, int columnNum){
		if(null!=get(rowNum,columnNum)){
			tableMap.get(rowNum).remove(columnNum);
			if(tableMap.get(rowNum).size()==0){
				tableMap.remove(rowNum);
			}
		}
	}
	
	/**
	 * 表格行数
	 * @return
	 */
	public int getRowSize(){
		return tableMap.size();
	}
	
	/**
	 * 表格列数
	 * @return
	 */
	public int getColumnSize(){
		return (tableMap.size()>0)?tableMap.get(0).size():0;
	}
	
	/**
	 * 确认值是否在该列中，返回格式为“row:column”的字符串列表，不存在返回null
	 * @param column
	 * @param value
	 * @return
	 */
	public List<String> columnSearch(int column,String value){
		List<String> resultList = new ArrayList<String>();
		int rowSize = this.getRowSize();
		for(int row=0; row<rowSize; row++){
			if(this.get(row, column).equals(value)){
				resultList.add(row+":"+column);
			}
		}
		return resultList.size()>0?resultList:null;
	}
	
	/**
	 * 表格按列排序
	 * @param column
	 * @param values
	 * @return
	 */
	public Table sort(int column, String[] values){
		int groupStartRow = 0;
		int columnSize = getColumnSize();
		for(int i=0; i<values.length; i++){
			List<String> coordinateList = columnSearch(column, values[i]);
			if(null!=coordinateList){
				int listSize = coordinateList.size();
				for(int j=0;j<listSize;j++){
					int cellRowNum = Integer.valueOf(coordinateList.get(j).split(":")[0]);
						for(int c=0; c<columnSize; c++){
							String cellValue = get(j+groupStartRow, c);
							put(j+groupStartRow, c, get(cellRowNum, c));
							put(cellRowNum, c, cellValue);
						}
						this.print();
				}
				groupStartRow+=listSize;
			}
		}
		
		return this;
	}
	
	/**
	 * 获取表格某一列的数据值，重复值仅取一次
	 * @param column
	 * @param type
	 * @return
	 */
	public String[] columnValues(int column,String type){
		Set<Integer> set = new TreeSet<Integer>();
		int rowNum = getRowSize();
		for(int i=0;i<rowNum;i++){
			set.add(Integer.valueOf(get(i, column)));
		}
		
		Object[] objects = set.toArray();
		
		List<String> list = new ArrayList<String>();
		
		for(Object o : objects){
			list.add(String.valueOf(o));
		}
		
		if(DESC.equals(type)){
			int listSize = list.size();
			for(int j=0;j<listSize/2;j++){
				String v = list.get(j);
				list.set(j, list.get(listSize-j-1));
				list.set(listSize-j-1, v);
			}
		}
		String[] values = (String[])list.toArray(new String[list.size()]);
		return values.length>0?values:null;
	}
	
	/**
	 * 表格行列转换,仅转换表格，表头在JXLUtils中转换
	 * @return
	 */
	public Table transform(){
		int row = this.getRowSize();
		int column = this.getColumnSize();
		String value = null;

		//转换
		if(row<column){
			for(int i=0;i<row;i++){
				for(int j=i;j<column;j++){
					value = get(j, i);
					put(j, i, get(i, j));
					if(value == null){
						clear(i,j);
					}else{
						put(i, j, value);
					}
				}
			}
			
		}else{
			for(int j=0;j<column;j++){
				for(int i=j;i<row;i++){
					value = get(j, i);
					put(j, i, get(i, j));
					if(value == null){
						clear(i,j);
					}else{
						put(i, j, value);
					}
				}
			}
		}
		return this;
	}
	
	public void print(){
		System.out.print("\n");
		for(int i=0;i<getRowSize();i++){
			for(int j=0;j<getColumnSize();j++){
				String value = get(i, j);
				System.out.print(value+"\t");
			}
			System.out.print("\n");
		}
	}

}

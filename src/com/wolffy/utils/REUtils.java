package com.wolffy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wolffy.model.Table;

/**
 * 列数据正则表达式截取
 * @author wolffy
 *
 */
public class REUtils {

	private static String emptyStr = "";
	
	/**
	 * 数据转义
	 * @param table
	 * @param expression
	 */
	public static Table re(Table table, String expression){
		
		boolean setModel = false;
		
		if(expression.startsWith("s")||expression.startsWith("S")){
			expression = expression.substring(1);
			setModel = true;
		}
		
		List<HashMap<String, String>> mapList = analysis(expression,setModel);
		
		Pattern p = null;
		Matcher m = null;
		
		for(Map<String, String> map : mapList){
			int column = Integer.parseInt(map.get("c"));
			String setExprStr = String.valueOf(map.get("s"));
			String exprStr = String.valueOf(map.get("v"));
			int rowSize = table.getRowSize();
			for(int row=0; row<rowSize; row ++){
				String value = table.get(row, column);
				table.put(row, column, emptyStr);
				if(setModel){
					p = Pattern.compile(setExprStr);
					m = p.matcher(value);
					if(m.find()){
						value = m.group(1);
					}
				}
				p = Pattern.compile(exprStr);
				m = p.matcher(value);
				if(setModel){
					while(m.find()){
						table.put(row, column, m.group(1));
						row+=1;
					}
					break;
				}else{
					if(m.find()){
						//仅替换第一个分组
						table.put(row, column, m.group(1));
					}
				}
			}
		}
		return table;
	}
	
	/**
	 * 表达式解析
	 * @param expression
	 */
	public static List<HashMap<String, String>> analysis(String expression,boolean setModel){
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
		
		String[] strs = expression.split(";");
		
		for(String str : strs){
			String[] column_value = str.split("\"");
			if(column_value.length==2){
				Map<String,String> map = new HashMap<String,String>();
				//c:列；s：set集合的提取的正则表达式；v：数值提取的正则表达式；
				map.put("c", column_value[0]);
				if(setModel){
					String[] set_value = column_value[1].split("->");
					map.put("s", set_value[0]);
					map.put("v", set_value[1]);
				}else{
					map.put("v", column_value[1]);
				}
				mapList.add((HashMap<String, String>) map);
			}
		}
		
		return mapList;
	}

}

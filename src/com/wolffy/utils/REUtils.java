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

	/**
	 * 数据转义
	 * @param table
	 * @param expression
	 */
	public static Table re(Table table, String expression){
		List<HashMap<String, String>> mapList = analysis(expression);
		
		Pattern p = null;
		Matcher m = null;
		
		for(Map<String, String> map : mapList){
			int column = Integer.parseInt(map.get("c"));
			String exprStr = String.valueOf(map.get("v"));
			int rowSize = table.getRowSize();
			for(int row=0; row<rowSize; row ++){
				String value = table.get(row, column);
				p = Pattern.compile(exprStr);
				m = p.matcher(value);
				if(m.find()){
					//仅替换第一个分组
					table.put(row, column, m.group(1));
				}
			}
		}
		return table;
	}
	
	/**
	 * 表达式解析
	 * @param expression
	 */
	public static List<HashMap<String, String>> analysis(String expression){
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
		
		String[] strs = expression.split(";");
		
		for(String str : strs){
			String[] column_value = str.split("\"");
			if(column_value.length==2){
				Map<String,String> map = new HashMap<String,String>();
				map.put("c", column_value[0]);
				map.put("v", column_value[1]);
				mapList.add((HashMap<String, String>) map);
			}
		}
		
		return mapList;
	}

}

package com.wolffy.utils;


import com.wolffy.model.Table;

/**
 * 表结构按列排序
 * @author wolffy
 *
 */
public class ColumnSortUtils {

	/**
	 * 数据转义
	 * @param table
	 * @param expression
	 */
	public static Table sort(Table table, String expression){
		
		String[] column_value = expression.split("\"");
		if(2==column_value.length){
			int column = Integer.valueOf(column_value[0]);
			
			if(column_value[1].equals("%sort_num_aesc")){
				table.sort(column, table.columnValues(column, Table.AESC));
			}else if(column_value[1].equals("%sort_num_desc")){
				table.sort(column, table.columnValues(column, Table.DESC));
			}else{
				String[] values = column_value[1].split(",");
				if(values.length>0){
					table.sort(Integer.valueOf(column_value[0]), values);
				}
			}
			

		}
		
		return table;
	}
	

}

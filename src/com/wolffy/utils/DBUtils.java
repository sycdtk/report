package com.wolffy.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.wolffy.manager.LoggerManager;
import com.wolffy.manager.PropsManager;
import com.wolffy.model.Table;

import jodd.db.DbQuery;
import jodd.db.DbSession;
import jodd.db.connection.ConnectionProvider;
import jodd.db.connection.DriverManagerConnectionProvider;

/**
 * 数据库操作工具类
 * @author wolffy
 *
 */
public class DBUtils {
	
	private static Logger logger = LoggerManager.getLogger(DBUtils.class.getName());  
	
	/**
	 * 连接数据库
	 * @throws SQLException 
	 */
	public static Map<Integer,Table> queryDB(Map<Integer,String> sqlMap) throws Exception{
		
		String url = PropsManager.getValue("db.url");
		String driver = PropsManager.getValue("db.driver");
		String username = PropsManager.getValue("db.username");
		String password = PropsManager.getValue("db.password");
		String beginDate = PropsManager.getValue("query.beginDate");
		String endDate = PropsManager.getValue("query.endDate");
		
		logger.info("读取数据库配置成功！"); 
		logger.config(url);
		
		Map<Integer,Table> tableMap = new HashMap<Integer,Table>();
		
		ConnectionProvider cp = new DriverManagerConnectionProvider(driver, url, username, password);
		
		//初始化，实际上就是加载驱动的过程。  
		cp.init();
		DbSession session = new DbSession(cp); 
		
		logger.info("数据库连接查询中……"); 
		
		for(Map.Entry<Integer, String> entry : sqlMap.entrySet()){
			
			String sql = entry.getValue();
			
			DbQuery query = new DbQuery(session, sql);
//			query.setDebugMode();
			if(beginDate!=null){
				query.setString("beginDate", beginDate);
			}
			if(endDate!=null){
				query.setString("endDate", endDate);
			}
			
			ResultSet rs = query.execute();
			
			int row = 0;
			int columnCount = rs.getMetaData().getColumnCount();
			Table table = new Table();
			
			//ResultSet由1开始
			while(rs.next()){
				for (int column=0; column<columnCount; column++) {
					table.put(row, column, rs.getString(column+1));
	            } 
				row++;
			}
			
			tableMap.put(entry.getKey(), table);
			
			query.close();
			
			logger.config(sql+"\n查询完成！");
		}
		return tableMap;
	}
}

package com.wolffy.report;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.wolffy.manager.LoggerManager;
import com.wolffy.manager.PropsManager;
import com.wolffy.model.Table;
import com.wolffy.utils.DBUtils;
import com.wolffy.utils.HelpUtils;
import com.wolffy.utils.JXLUtils;
import com.wolffy.utils.JacobUtils;

/**
 * 数据库导出excel报表工具
 * @author wolffy
 *
 */
public class Report {

	private static Logger logger = LoggerManager.getLogger(Report.class.getName());
	
	
	/**
	 * 报表数据统计工具
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		
		
		HelpUtils.init();
		
		Map<Integer,String> sqlMap = new HashMap<Integer,String>();
		
		//动态加载配置文件
		if(args.length>0){
			if(args[0].endsWith(".properties")){
				String filename = args[0];
				logger.info("加载"+filename+"文件！");
				PropsManager.reload(filename);
				logger.info("文件"+filename+"加载成功！");
				
				String[] reportCount = PropsManager.getValue("report.count").split(",");
				for(String count : reportCount){
					sqlMap.put(Integer.parseInt(count), PropsManager.getValue("report."+count+".sql"));
				}
				
				logger.info("读取sql成功！");
				
				logger.info("开始数据查询……");
				
				Map<Integer,Table> tableMap = null;
				try {
					tableMap = DBUtils.queryDB(sqlMap);
				} catch (Exception e) {
					logger.severe(e.getMessage());
					e.printStackTrace();
				}
				
				logger.info("数据查询成功！");
				
				logger.info("开始数据导出……");
				
				JXLUtils.DB2Excel(tableMap);
				
				logger.info("数据导出成功！");
				
				
				String jacobChart = PropsManager.getValue("jacob.chart");
				if(jacobChart.trim().toLowerCase().equals("true")){
					logger.info("开始生成图表……");
					JacobUtils.drawChart();
					logger.info("图表生成成功！");
				}
				
			}else{
				HelpUtils.search(args[0]);
			}
		}

	}
	
}

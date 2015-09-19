package com.wolffy.utils;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import com.wolffy.manager.LoggerManager;
import com.wolffy.manager.PropsManager;
import com.wolffy.model.Table;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 生成Excel文档
 * @author wolffy
 *
 */
public class JXLUtils {
	
	private static Logger logger = LoggerManager.getLogger(JXLUtils.class.getName());  
	
	public static boolean DB2Excel(Map<Integer,Table> tableMap) { 
        boolean flag = false; 
        WritableWorkbook workBook = null; 
        WritableSheet sheet = null; 
        Label label = null;
        Number value = null;
        
		String excelPath = PropsManager.getValue("excel.path");
		excelPath = excelPath==null?System.getProperty("user.dir"):excelPath;
		String excelName = PropsManager.getValue("excel.name");
		Integer numberMaxLength = Integer.parseInt(PropsManager.getValue("excel.numberMaxLength"));
//		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		excelName = excelName.replaceAll("{date}", date);
		
		String[] reportCount = PropsManager.getValue("report.count").split(",");
		
		WritableCellFormat contentFromart = new WritableCellFormat(NumberFormats.TEXT);

        try {
            // 创建Excel表 
            workBook = Workbook.createWorkbook(new File(excelPath+System.getProperty("file.separator")+excelName)); 
            
            //遍历sheet
            for(String countStr : reportCount){
            	int count = Integer.parseInt(countStr);
                // 创建Excel表中的sheet 
            	String sheetName = PropsManager.getValue("report."+count+".name");
            	sheet = workBook.createSheet(sheetName, count); 
            	
            	//获取列头
            	String columnNameStr = PropsManager.getValue("report."+count+".columnName");
            	String[] columnNames = columnNameStr.split(",");
            	
            	//获取数据表
            	Table table = tableMap.get(count);
            	int columnCount = columnNames.length;
            	
            	//行列转换
            	String transStr = PropsManager.getValue("report."+count+".trans");
            	boolean trans = false;
            	if(transStr!=null){
            		trans = transStr.toLowerCase().equals("true")?true:false;
            	}
            			
            	if(trans){
            		// 转换,添加列头
                    for (int i = 0; i < columnCount; i++) { 
                        label = new Label(0,i, columnNames[i]); 
                        sheet.addCell(label); 
                    } 
            	}else{
            		// 不转换，添加行头
                    for (int i = 0; i < columnCount; i++) { 
                        label = new Label(i, 0, columnNames[i]); 
                        sheet.addCell(label); 
                    } 
            	}
            	
                
                //补全缺失数据
                String cExpression = PropsManager.getValue("report."+count+".completion");
                if(cExpression!=null){
                	table = CompletionUtils.completion(table, cExpression);
                }
                
                //数据解析
                String reExpression = PropsManager.getValue("report."+count+".re");
                if(reExpression!=null){
                	table = REUtils.re(table, reExpression);
                }
                
                //数据转义
                String eExpression = PropsManager.getValue("report."+count+".escape");
                if(eExpression!=null){
                	table = EscapeUtils.escape(table, eExpression);
                }
                
                //数据排序
                String sExpression = PropsManager.getValue("report."+count+".sort");
                if(sExpression!=null){
                	table = ColumnSortUtils.sort(table, sExpression);
                }
                
                //行列转换
                if(trans){
                	table.transform();
                }
                
            	//添加数据
                int rowCount = table.getRowSize();
                
                for(int row=0; row<rowCount; row++){
                	for(int column=0;column<columnCount;column++){
                		String cellValue = table.get(row, column);
                		//字符串是否为整数值
                		if(cellValue!=null&&!cellValue.equals("")&&cellValue.matches("[0-9]*")){
                			
                			if(cellValue.length()>numberMaxLength){
                				//数据行列转换
                				if(trans){
                					//转换
                					label = new Label(column+1, row, cellValue, contentFromart);
                				}else{
                					//不转换
                					label = new Label(column, row+1, cellValue, contentFromart);
                				}
                    			sheet.addCell(label); 
                			}else{
                				//数据行列转换
                				if(trans){
                					//转换
                					value = new Number(column+1, row, Long.parseLong(cellValue));
                				}else{
                					//不转换
                					value = new Number(column, row+1, Long.parseLong(cellValue));
                				}
                    			
                    			sheet.addCell(value); 
                			}
                		}else{
                			//数据行列转换
            				if(trans){
            					//转换
            					label = new Label(column+1, row, cellValue);
            				}else{
            					//不转换
            					label = new Label(column, row+1, cellValue);
            				}
                			
                			sheet.addCell(label); 
                		}
                        
                	}
                }
                logger.config("编号为"+(count+1)+"报表导出完成，共"+reportCount.length+"张。");
            }

            // 关闭文件 
            workBook.write();
            flag = true;
            
            } catch (Exception e) { 
            	logger.severe(e.getMessage());
                e.printStackTrace(); 
            } finally { 
                try { 
                    workBook.close(); 
                } catch (Exception e) {
                	logger.severe(e.getMessage());
                    e.printStackTrace(); 
                } 
            } 
        return flag; 
    } 

}

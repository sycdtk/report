package com.wolffy.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * 帮助文档
 * @author wolffy
 *
 */
public class HelpUtils {

	private static Map<String,String> helpMap = new HashMap<String,String>();
	
	public static void search(String keys){
		String result="\n";
		for(Map.Entry<String, String>m : helpMap.entrySet()){
			if(m.getKey().contains(keys)){
				result+=m.getValue();
				result+="\n";
			}
		}
		sayHi();
		System.out.println(result);
		
	}
	
	public static void init(){
		String key1 = "db url driver username password";
		String value1 = "*数据库连接配置:\n"+
				"\n"+
				"oracle:\n"+
				"\tjdbc:oracle:thin:@localhost:1521/orcl\n"+
				"\toracle:oracle.jdbc.driver.OracleDriver\n"+
				"\n"+
				"db2:\n"+
				"\tjdbc:db2://localhost:50000/dbname\n"+
				"\tdb2:com.ibm.db2.jcc.DB2Driver\n"+
				"\n"+
				"mysql:\n"+
				"\tjdbc:mysql://localhost:3306/dbname\n"+
				"\tmysql:com.mysql.jdbc.Driver\n"+
				"\n"+
				"sqlserver:\n"+
				"\tjdbc:microsoft:sqlserver://localhost:1433;DatabaseName=dbname\n"+
				"\tsqlserver:com.microsoft.jdbc.sqlserver.SQLServerDriver\n"+
				"\n"+
				"mysql设置样例：\n"+
				"\tdb.url=jdbc:mysql://localhost:3306/dbname\n"+
				"\tdb.driver=com.mysql.jdbc.Driver\n"+
				"\tdb.username=root\n"+
				"\tdb.password=root\n";
		helpMap.put(key1, value1);
		
		String key2 = "query begindate enddate";
		String value2 = "*查询日期:\n"+
				"\n"+
				"报表查询的开市结束日期，用于report.*.sql调用，调用方式为：\n" +
				"\tselect * from tab_name where create_time between :beginDate and :endDate\n" +
				"日期格式：\n"+
				"\tyyyy-mm-dd hh:MM:ss\n"+
				"\n"+
				"设置样例：\n" +
				"\tquery.beginDate=2015-01-01 00:00:00\n"+
				"\tquery.endDate=2015-12-30 23:59:59\n";		
		helpMap.put(key2, value2);
		
		String key3 = "excel path name numberMaxLength";
		String value3 = "*导出文件设置:\n"+
				"\n" +
				"excel.path:\n" +
				"\t导出excel文档的绝对路径，注意：路径最后不带路径分隔符separator\n"+
				"\tpath值为空时，为当前目录\n"+
				"\n" +
				"excel.name:\n" +
				"\t导出excel文档的名称\n"+
				"\n" +
				"excel.numberMaxLength:\n" +
				"\t在excel中，数据串超过numberMaxLength的值以文本格式显示，低于numberMaxLength的值以数字格式显示\n"+
				"\n"+
				"设置样例：\n" +
				"\texcel.path=d:\n"+
				"\texcel.name=report.xls\n"+
				"\texcel.numberMaxLength=8\n";
		helpMap.put(key3, value3);
		
		
		
		String key4 = "report count";
		String value4 = "*报表编号:\n"+
				"\n"+
				"此处设置了报表编号的报表，才会导出到excel文档中，可以针对场景设置多个备注，方便切换。其中编号为report.*.name等中的数字编号：\n" +
				"\treport.0.name=事件影响程度统计\n" +
				"\treport.0.columnName=影响程度,数量\n" +
				"\treport.0.sql=select * from ……\n" +
				"\treport.0.completion=\n" +
				"\t……\n" +
				"\n"+
				"设置样例：\n" +
				"\treport.count=0,1,2,3,4,5,6,7,8,9,10,11\n"+
				"\t#月报\n"+
				"\t#report.count=21,24,25,29,30,31,34,35,36,37,38,39\n"+
				"\t#事件负责人\n"+
				"\t#report.count=21,24,25,29,30,31\n";	
		helpMap.put(key4, value4);
		
		String key5 = "log level file";
		String value5 = "*日志输出:\n"+
				"\n"+
				"log.level：\n" +
				"\t日志的输出级别，级别分为两级：INFO,CONFIG。INFO：运行：CONFIG：调试\n" +
				"\n"+
				"log.file：\n" +
				"\t日志文件名，路径为默认软件当前目录，若值为空则不输出日志\n" +
				"\n"+
				"设置样例：\n" +
				"\tlog.level=CONFIG\n"+
				"\tlog.file=report.log\n";
		helpMap.put(key5, value5);

		String key6 = "log level file";
		String value6 = "*日志输出:\n"+
				"\n"+
				"log.level：\n" +
				"\t日志的输出级别，级别分为两级：INFO,CONFIG。INFO：运行：CONFIG：调试\n" +
				"\n"+
				"log.file：\n" +
				"\t日志文件名，路径为默认软件当前目录，若值为空则不输出日志\n" +
				"\n"+
				"设置样例：\n" +
				"\tlog.level=CONFIG\n"+
				"\tlog.file=report.log\n";
		helpMap.put(key6, value6);
		
		String key7 = "jacob chart dll width height prefix chartCategory";
		String value7 = "*自动生成excel图表:\n"+
				"\n"+
				"*注意：使用此功能需要在安装有MS office的机器上运行，且jacob.dll需要配置为对应系统结构（x86/x64）\n" +
				"\n"+
				"jacob.chart：\n" +
				"\t是否绘制图表,true:绘制，false:不绘制。不绘制时，jacob.*以及report.*.chartTypes属性均不会生效\n" +
				"\n"+
				"jacob.dll：\n" +
				"\tjacob相关dll文件名称，分为x86和x64两种，本工具使用的为jacob-1.17-x86.dll和jacob-1.17-x64.dll\n" +
				"\n"+
				"jacob.chart.width/height：\n" +
				"\t生成图表的宽度及高度\n" +
				"\n"+
				"jacob.chart.prefix：\n" +
				"\t图表标题的前缀，前缀允许为空\n" +
				"\n"+
				"jacob.chart.chartCategory：\n" +
				"\t图表类型编号定义，用以配置report.*.chartTypes所能够绘制的类型。支持更多类型，但本工具中使用需要做相应开发，类型编号参见下方：\n"+
				"\t\tjacob.chart的类型编号说明\n"+
				"\t\t柱形图 簇状柱形图 51 \n"+
				"\t\t三维簇状柱形图 54 \n"+
				"\t\t堆积柱形图 52 \n"+
				"\t\t三维堆积柱形图 55 \n"+
				"\t\t百分比堆积柱形图 53 \n"+
				"\t\t三维百分比堆积柱形图 56 \n"+
				"\t\t三维柱形图 -4100 \n"+
				"\t\t条形图 簇状条形图 57 \n"+
				"\t\t三维簇状条形图 60 \n"+
				"\t\t堆积条形图 58 \n"+
				"\t\t三维堆积条形图 61 \n"+
				"\t\t百分比堆积条形图 59 \n"+
				"\t\t三维百分比堆积条形图 62 \n"+
				"\t\t折线图 折线图 4 \n"+
				"\t\t数据点折线图 65 \n"+
				"\t\t堆积折线图 63 \n"+
				"\t\t堆积数据点折线图 66 \n"+
				"\t\t百分比堆积折线图 64 \n"+
				"\t\t百分比堆积数据点折线图 67 \n"+
				"\t\t三维折线图 -4101 \n"+
				"\t\t饼图 饼图 5 \n"+
				"\t\t分离型饼图 69 \n"+
				"\t\t三维饼图 -4102 \n"+
				"\t\t三维分离型饼图 70 \n"+
				"\t\t复合饼图 68 \n"+
				"\t\t复合柱饼图 71 \n"+
				"\t\tXY (散点图) 散点图 -4169 \n"+
				"\t\t平滑线散点图 72 \n"+
				"\t\t无数据点平滑线散点图 73 \n"+
				"\t\t折线散点图 74 \n"+
				"\t\t无数据点折线散点图 75 \n"+
				"\t\t气泡图 气泡图 15 \n"+
				"\t\t三维气泡图 87 \n"+
				"\t\t面积图 面积图 1 \n"+
				"\t\t三维面积图 -4098 \n"+
				"\t\t堆积面积图 76 \n"+
				"\t\t三维堆积面积图 78 \n"+
				"\t\t百分比堆积面积图 77 \n"+
				"\t\t三维百分比堆积面积图 79 \n"+
				"\t\t圆环图 圆环图 -4120 \n"+
				"\t\t分离型圆环图 80 \n"+
				"\t\t雷达图 雷达图 -4151 \n"+
				"\t\t数据点雷达图 81 \n"+
				"\t\t填充雷达图 82 \n"+
				"\t\t曲面图 三维曲面图 83 \n"+
				"\t\t曲面图（俯视图） 85 \n"+
				"\t\t三维曲面图（框架图） 84 \n"+
				"\t\t曲面图（俯视框架图） 86 \n"+
				"\t\t股价图 盘高-盘低-收盘图 88 \n"+
				"\t\t成交量-盘高-盘低-收盘图 90 \n"+
				"\t\t开盘-盘高-盘低-收盘图 89 \n"+
				"\t\t成交量-开盘-盘高-盘低-收盘图 91 \n"+
				"\t\t圆柱图 簇状柱形圆柱图 92 \n"+
				"\t\t簇状条形圆柱图 95 \n"+
				"\t\t堆积柱形圆柱图 93 \n"+
				"\t\t堆积条形圆柱图 96 \n"+
				"\t\t百分比堆积柱形圆柱图 94 \n"+
				"\t\t百分比堆积条形圆柱图 97 \n"+
				"\t\t三维柱形圆柱图 98 \n"+
				"\t\t圆锥图 簇状柱形圆锥图 99 \n"+
				"\t\t簇状条形圆锥图 102 \n"+
				"\t\t堆积柱形圆锥图 100 \n"+
				"\t\t堆积条形圆锥图 103 \n"+
				"\t\t百分比堆积柱形圆锥图 101 \n"+
				"\t\t百分比堆积条形圆锥图 104 \n"+
				"\t\t三维柱形圆锥图 105 \n"+
				"\t\t棱锥图 簇状柱形棱锥图 106 \n"+
				"\t\t簇状条形棱锥图 109 \n"+
				"\t\t堆积柱形棱锥图 107         \n"+
				"\t\t堆积条形棱锥图 110 \n"+
				"\t\t百分比堆积柱形棱锥图 108 \n"+
				"\t\t百分比堆积条形棱锥图 111 \n"+
				"\t\t三维柱形棱锥图 112 \n"+
				"\t图表标题的前缀，前缀允许为空\n"+
				"\n"+
				"jacob.chart.prefix：\n" +
				"\t图表标题的前缀，前缀允许为空\n" +
				"\n"+
				"设置样例：\n" +
				"\tjacob.chart=false\n" +
				"\tjacob.dll=jacob-1.17-x64.dll\n" +
				"\tjacob.chart.width=400\n" +
				"\tjacob.chart.height=250\n" +
				"\tjacob.chart.prefix=十二月\n" +
				"\tjacob.chart.chartCategory=bar:51,line:4,pie:-4102\n";
		helpMap.put(key7, value7);
		
		String key8 = "report name columnName sql completion re escape sort trans chartTypes";
		String value8 = "*报表设置:\n"+
				"\n"+
				"report.*.name：\n" +
				"\t报表的名称\n" +
				"\n"+
				"report.*.columnName：\n" +
				"\t报表各个列的列名，数量与sql的字段名数量相同\n" +
				"\n"+
				"report.*.sql：\n" +
				"\t数据查询sql\n" +
				"\n"+
				"report.*.completion：\n" +
				"\t列数据补全设置，针对列数据内容不完整的数据进行补全，并设置补全数据的默认值，目前仅支持一列数据补全。例如有二级、三级事件，但没有一级事件，可将一级事件补全并设置值为0\n" +
				"\treport.*.completion=0基列值\"数据1，数据2……:数值\n" +
				"\t数据1，数据2……为列中数据全集，数值为补全数据默认值\n" +
				"\n"+
				"report.*.re：\n" +
				"\t通过正则分组，提取某个字段的数据值。由于正则符号需要转义，所以数据格式仅支持   name:value,name:value, 的格式保存的数据，且最后一个逗号为必须的。\n" +
				"\t每一列的仅有一个正则表达式，每个表达式仅有一个分组，数据提取的及为分组数据\n" +
				"\treport.*.re=0基列数\"java正则表达式;…… \n" +
				"\n"+
				"report.*.escape：\n" +
				"\t数据转义，将数据值转换为实际含义。例如数据值为1、2、3实际含义为一级、二级、三级\n" +
				"\t每一列的仅有一个正则表达式，每个表达式仅有一个分组，数据提取的及为分组数据\n" +
				"\treport.*.escape=列数\"原值:转义值,原值:转义值;…… \n" +
				"\n" +
				"report.*.sort：\n" +
				"\t列数据排序，目前仅支持一列数据排序。排序方式分为指定顺序排序和数值排序两类\n" +
				"\t排序以输入顺序为顺序，若数据存在未包含的字段值，则排列在最后。若某一列需要按数据值排序，采用%sort_num_aesc实现顺序排序，采用%sort_num_desc实现逆序排序\n" +
				"\treport.*.sort=0基列数\"字段值1,字段值2…… \n" +
				"\treport.*.sort=0基列数列\"%sort_num_desc\n" +
				"\n" +
				"report.*.trans:\n" +
				"\t行列互换，true为互换，空或false为不互换\n"+
				"\treport.*.trans=true\n" +
				"\n" +
				"report.*.chartTypes:\n" +
				"\tchartTypes的图表类型通过jacob.chart.chartType进行配置获取\n"+
				"\tchartTypes的参数值以下方为准：\n"+
				"\t1、数值，要应用的数据标签的类型。其值如下：\n"+
				"\t   6 显示关于绝对值的气泡图的大小。 \n"+
				"\t   4 数据点所属的分类。 \n"+
				"\t   5 占总数的百分比及数据点所属的分类。仅用于饼图和圆环图。 \n"+
				"\t   -4142 无数据标签。 \n"+
				"\t   3 占总数的百分比。仅用于饼图和圆环图。 \n"+
				"\t   2 数据点的默认值（如果未指定此参数）。 \n"+
				"\t2、boolean，如果为 True，则在数据点旁边显示图例项标示。默认值为 False。 \n"+
				"\t3、boolean，如果对象根据内容自动生成相应的文字，则为 True。 \n"+
				"\t4、boolean，可选 Variant 对于 Chart 和 Series 对象，如果数据系列有引导线，则为 True。 \n"+
				"\t5、boolean，传递布尔值以启用或禁用数据标签的系列名称。 \n"+
				"\t6、boolean，传递布尔值以启用或禁用数据标签的分类名称。 \n"+
				"\t7、boolean，传递布尔值以启用或禁用数据标签的值。 \n"+
				"\t8、boolean，传递布尔值以启用或禁用数据标签的百分比。 \n"+
				"\t9、boolean，传递布尔值以启用或禁用数据标签的气泡大小。 \n" +
				"report.*.chartTypes=图表类型:参数值1,参数值2……，参数值9:最大行数(可选)" +
				"柱状图样例：report.*.chartTypes=bar:2,false,false,true,false,false,true,true,false:" +
				"饼图样例：report.*.chartTypes=pie:2,false,true,true,false,false,true,true,false:"+
				"\n" +
				"\n"+
				"设置样例：\n" +
				"\n"+
				"\t*样例1\n"+
				"\t\treport.0.name=事件影响程度统计\n"+
				"\t\treport.0.columnName=影响程度,数量\n"+
				"\t\treport.0.sql=select e.name,count(e.name) from incident as i left join admin_incident_effect as e on i.effect_id=e.admin_incident_effect_id where create_time between :beginDate and :endDate group by e.name\n"+
				"\t\treport.0.completion=0\"1级-造成服务中断，严重影响服务可用性。,2级-未造成服务中断，对服务质量有影响。,3级-未造成服务中断，对服务运行存在或可能存在隐患。:0\n"+
				"\t\treport.0.re="+
				"\t\treport.0.escape=\n" +
				"\t\treport.0.sort=\n"+
				"\t\treport.0.trans=\n"+
				"\t\treport.0.chartTypes=pie:2,false,true,true,false,false,true,true,false:\n" +
				"\n" +
				"\t*样例2\n" +
				"\t\treport.1.name=系统资源统计\n"+
				"\t\treport.1.columnName=系统名称,CPU,SWAP,MEM,/,/boot\n"+
				"\t\treport.1.sql=select aj_hostname,aj_returndesc as cpu,aj_returndesc as swap,aj_returndesc as mem,aj_returndesc path1,aj_returndesc as path2 from aj_curcheckresult \n"+
				"\t\treport.1.completion=\n"+
				"\t\treport.1.re=1\"cpu:(\\w+),;2\"swap:(\\w+\\%),;3\"mem:(\\w+\\%),;4\"/:(\\w+\\%),;5\"/boot:(\\w+\\%),\n"+
				"\t\treport.1.escape=\n"+
				"\t\treport.1.sort=\n"+
				"\t\treport.1.trans=true\n"+
				"\t\treport.1.chartTypes=\n" +
				"\n" +
				"\t*样例3\n" +
				"\t\treport.2.name=事件级别统计\n"+
				"\t\treport.2.columnName=级别,数量\n"+
				"\t\treport.2.sql=select PRIORITY,count(1) from INCIDENT where CREATE_TIME between :beginDate and :endDate group by PRIORITY order by PRIORITY\n"+
				"\t\treport.2.completion=0\"1,2,3,4,5,6,7:0\n"+
				"\t\treport.2.re=\n"+
				"\t\treport.2.escape=0\"1:一级,2:二级,3:三级,4:四级,5:五级,6:六级,7:七级;\n"+
				"\t\treport.2.sort=\n"+
				"\t\treport.2.trans=\n"+
				"\t\treport.2.chartTypes=pie:2,false,true,true,false,false,true,true,false:\n"+
				"\n" +
				"\t*样例4\n" +
				"\t\treport.3.name=机房出入单据类型统计\n"+
				"\t\treport.3.columnName=关联工单类型,数量\n"+
				"\t\treport.3.sql=select order_type,count(order_type) from visit_application where create_time between :beginDate and :endDate group by order_type order by count(order_type) desc\n"+
				"\t\treport.3.completion=\n"+
				"\t\treport.3.re=\n"+
				"\t\treport.3.escape=0\"1:问题,2:变更,3:发布,4:事件,5:巡检,6:维护,7:其它;\n"+
				"\t\treport.3.sort=\n"+
				"\t\treport.3.trans=\n"+
				"\t\treport.3.chartTypes=bar:2,false,false,true,false,false,true,true,false:";
		helpMap.put(key8, value8);
	}

	private static void sayHi(){
		System.out.println("************************************************************");
		System.out.println("*                  Report Tools V2.1                       *");
		System.out.println("*                  Author : Li Rui                         *");
		System.out.println("*                  E-mail : 78306909@qq.com                *");
		System.out.println("*                  Date   : 2015-09-19                     *");
		System.out.println("************************************************************");
	}
	
}

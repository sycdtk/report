package com.wolffy.manager;

import java.io.File;
import java.io.IOException;

import jodd.props.Props;

/**
 * 资源文件加载类
 * @author wolffy
 *
 */
public class PropsManager {
	
	private static Props p = null;
	
	static{
		p = new Props();
		try {
			p.load(new File("report.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return PropsManager.p.getValue(key);
	}
	
	public static void reload(String filename){
		p = new Props();
		try {
			p.load(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

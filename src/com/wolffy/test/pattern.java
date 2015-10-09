package com.wolffy.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		系统
//		String x = "/boot:6%,/:22%,cpu:0,mem:44%,swap:0%,";
//		Pattern p = Pattern.compile("/:(\\w+\\%),");
//		Matcher m = p.matcher(x);
//		if(m.find()){
//			//仅替换第一个分组
//			System.out.println(m.group(1));
//		}
		
//		ITM
//		String x = "/opt#62%|cpu#3|mem#37%|runtime#  9:21am  up 1168 days 21:47|swap#29%|";
//		Pattern p = Pattern.compile("runtime#.*up\\s{1}(.*?(days|天)).*?\\|");
//		Matcher m = p.matcher(x);
//		if(m.find()){
//			//仅替换第一个分组
//			System.out.println(m.group(1));
//		}
		
//		oracle监控
		String x = "ww#|weblogic#BL685-181:LZ@BL685-294:LZ@MAS-SERVER:LZ@BL685-043:LZ@|os#|";
		Pattern p = Pattern.compile("ww#(.*?)\\|");
		Matcher m = p.matcher(x);
		while(m.find()){
			//仅替换第一个分组
			System.out.println(m.group(1));

		}
	}

}

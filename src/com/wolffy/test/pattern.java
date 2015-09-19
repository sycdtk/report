package com.wolffy.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "/boot:6%,/:22%,cpu:0,mem:44%,swap:0%,";
		Pattern p = Pattern.compile("/:(\\w+\\%),");
		Matcher m = p.matcher(x);
		if(m.find()){
			//仅替换第一个分组
			System.out.println(m.group(1));
		}
	}

}

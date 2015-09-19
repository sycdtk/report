package com.wolffy.test;


import com.wolffy.model.Table;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Table t = new Table();
		t.put(0, 0, "1");
		t.put(0, 1, "x0xxxx1");
		t.put(0, 2, "x0xxxx2");
		
		t.put(4, 0, "11");
		t.put(4, 1, "x4xxxx1");
		t.put(4, 2, "x4xxxx2");
		
		t.put(1, 0, "4");
		t.put(1, 1, "x3xxxx1");
		t.put(1, 2, "x3xxxx2");
		
		t.put(2, 0, "9");
		t.put(2, 1, "x2xxxx1");
		t.put(2, 2, "x2xxxx2");
		
		t.put(3, 0, "8");
		t.put(3, 1, "x4xxxx1");
		t.put(3, 2, "x4xxxx2");
		
		t.put(6, 0, "8");
		t.put(6, 1, "x5xxxx1");
		t.put(6, 2, "x5xxxx2");
		
		t.put(5, 0, "6");
		t.put(5, 1, "x6xxxx1");
		t.put(5, 2, "x6xxxx2");
		
		t.put(7, 0, "1");
		t.put(7, 1, "x7xxxx1");
		t.put(7, 2, "x7xxxx2");
		
		t.put(8, 0, "1");
		t.put(8, 1, "x9xxxx1");
		t.put(8, 2, "x9xxxx2");
		
		t.put(9, 0, "1");
		t.put(9, 1, "x8xxxx1");
		t.put(9, 2, "x8xxxx2");
		
		t.put(10, 0, "2");
		t.put(10, 1, "x8xxxx1");
		t.put(10, 2, "x8xxxx2");
		
		String[] v = {"1-xx","2-xx","3-xx","4-xx"};
		String[] w = {"x9xxxx2","x8xxxx2","x7xxxx2"};
		
		t.print();
		
		//t.sort(0, v);
		t.sort(0, t.columnValues(0, Table.DESC));

		t.print();
		t.transform();
		
		t.print();
	}

}

package com.wolffy.test;

public class string {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String x = "S0:adsfsdfs";
		if(x.startsWith("s")||x.startsWith("S")){
			x = x.substring(1);
		}
	
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				
				if(i==3&&j==4){
					break;
				}
				System.out.println(i+" "+j);
			}
			
		}
		
	}

}

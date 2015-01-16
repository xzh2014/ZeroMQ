package com.moji.zeromq.task;

public class Test {
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		for(int x = 0; x<= 2657; x++){
			for(int y = 0; y<= 96431.2/38.17; y++){
				for(int z=0; z<= 96431.2/28; z++){
					if(-0.00001f < ((36.3*x+38.17*y+28*z) - 96431.2) && ((36.3*x+38.17*y+28*z) - 96431.2) < 0.00001f){
						System.out.println("x="+x+" y="+y+" z="+z);
					}
				}
			}
		}
		System.out.println("总的计算用时:"+(System.currentTimeMillis()-start));
	}
}

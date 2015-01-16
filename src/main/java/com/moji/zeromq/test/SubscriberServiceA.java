package com.moji.zeromq.test;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class SubscriberServiceA {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket subscriber = cxt.socket(ZMQ.SUB);
		subscriber.connect("tcp://127.0.0.1:5559");
		subscriber.subscribe("A".getBytes());
		
		Socket publisher = cxt.socket(ZMQ.PUB);
		publisher.bind("tcp://127.0.0.1:6000");
		
		StringBuffer result = new StringBuffer();
		
		System.out.println(System.currentTimeMillis()+": 订阅任务开始...");
		String index[] = subscriber.recvStr().split(":");
		int startIndex = Integer.valueOf(index[1]);
		int endIndex = Integer.valueOf(index[2]);
		System.out.println(System.currentTimeMillis()+": 订阅任务结束...");
		
		System.out.println(System.currentTimeMillis()+": 计算任务开始...");
		for(int x = startIndex; x<= endIndex; x++){
			for(int y = 0; y<= 96431.2/38.17; y++){
				for(int z=0; z<= 96431.2/28; z++){
					if(-0.00001f < ((36.3*x+38.17*y+28*z) - 96431.2) && ((36.3*x+38.17*y+28*z) - 96431.2) < 0.00001f){
						result.append("x="+x+" y="+y+" z="+z+"\r\n");
						System.out.println("正在计算...");
					}
				}
			}
		}
		System.out.println(System.currentTimeMillis()+": 计算任务结束...");
		
		System.out.println(System.currentTimeMillis()+": 发布结算结果开始...");
		try {
			Thread.sleep(0);
			publisher.send(result.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(System.currentTimeMillis()+": 发布结算结果结束...");
		
		publisher.close();
		subscriber.close();
		cxt.term();
		
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis()+": A段计算开始...");
		Long start = System.currentTimeMillis();
		SubscriberServiceA.run();
		System.out.println(System.currentTimeMillis()+": A段计算结束...");
		System.out.println("计算用时:"+(System.currentTimeMillis()-start));
	}
}

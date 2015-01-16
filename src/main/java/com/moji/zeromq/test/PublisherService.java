package com.moji.zeromq.test;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class PublisherService {
	private final static String A = "A:";
	private final static String B = "B:";
	private final static String C = "C:";

	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket publisher = cxt.socket(ZMQ.PUB);
		publisher.bind("tcp://127.0.0.1:5559");
		
		try {
			Thread.sleep(2000);  
			publisher.send(A+"0:885");
			publisher.send(B+"886:1770");
			publisher.send(C+"1771:2657");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		publisher.close();
		cxt.term();
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis()+": 发布任务开始...");
		PublisherService.run();
		System.out.println(System.currentTimeMillis()+": 发布任务结束...");
	}
}

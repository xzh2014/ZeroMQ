package com.moji.zeromq.test;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class ResultService {
	public static void run(){
		Context cxt = ZMQ.context(3);
		Socket resultA = cxt.socket(ZMQ.SUB);
		resultA.connect("tcp://127.0.0.1:6000");
		resultA.subscribe("".getBytes());
		
		Socket resultB = cxt.socket(ZMQ.SUB);
		resultB.connect("tcp://127.0.0.1:6001");
		resultB.subscribe("".getBytes());
		
		Socket resultC = cxt.socket(ZMQ.SUB);
		resultC.connect("tcp://127.0.0.1:6002");
		resultC.subscribe("".getBytes());
		
		String result = null;
		
		String A = resultA.recvStr();
		String B = resultB.recvStr();
		String C = resultC.recvStr();
		result = A+B+C;
		
		System.out.println(result);
	}
	public static void main(String[] args) {
		System.out.println("等待计算结果..");
		Long start = System.currentTimeMillis();
		ResultService.run();
		System.out.println("总的计算用时:"+(System.currentTimeMillis()-start));
	}
}

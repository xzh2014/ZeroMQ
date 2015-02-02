package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author zhaohe.xiao
 * 消息分块接受例子
 */
public class MultipartMessageClient {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket socket = cxt.socket(ZMQ.SUB);
		socket.connect("tcp://127.0.0.1:9999");
		socket.subscribe("".getBytes());
		
		String part1 = null;
		String part2 = null;
		String part3 = null;
		part1 = socket.recvStr();
		while(true){
			part1 = socket.recvStr(0);
			if(socket.hasReceiveMore()){
				part2 = socket.recvStr(0);
			}
			if(socket.hasReceiveMore()){
				part3 = socket.recvStr(0);
			}
			
			System.out.println(part1+part2+part3);
		}
	}
	public static void main(String[] args) {
		MultipartMessageClient.run();
	}
}

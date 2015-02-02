package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author zhaohe.xiao
 *消息发布者B
 */
public class PubSubNetworkWithProxyPublisherB {
	public static void  run(){
		Context cxt = ZMQ.context(1);
		Socket socket = cxt.socket(ZMQ.PUB);
		socket.connect("tcp://127.0.0.1:6000");
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true){
			socket.send("B:等你晋升到");
			System.out.println("正在发送消息：B...");
		}
	}
	
	public static void main(String[] args) {
		PubSubNetworkWithProxyPublisherB.run();
	}
}

package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author zhaohe.xiao
 *消息订阅者B
 */
public class PubSubNetworkWithProxySubscriberB {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket sub = cxt.socket(ZMQ.SUB);
		sub.connect("tcp://127.0.0.1:7000");
		sub.subscribe("B".getBytes());
		while(true){
			String data = sub.recvStr();
			System.out.println(data);
		}
	}
	public static void main(String[] args) {
		PubSubNetworkWithProxySubscriberB.run();
	}
}

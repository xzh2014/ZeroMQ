package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author zhaohe.xiao
 * 消息代理
 */
public class PubSubNetworkWithProxy {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket xsub = cxt.socket(ZMQ.XSUB);
		xsub.bind("tcp://127.0.0.1:6000");
		Socket xpub = cxt.socket(ZMQ.XPUB);
		xpub.bind("tcp://127.0.0.1:7000");
		ZMQ.proxy(xsub, xpub, null);
		
		xsub.close();
		xpub.close();
		cxt.term();
	}
	public static void main(String[] args) {
		System.out.println("代理中...");
		PubSubNetworkWithProxy.run();
	}
}

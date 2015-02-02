package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * @author zhaohe.xiao
 *消息分块传送例子
 */
public class MultipartMessageServer {
	public static void run() {
		Context cxt = ZMQ.context(1);
		Socket socket = cxt.socket(ZMQ.PUB);
		socket.bind("tcp://127.0.0.1:9999");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true){
			socket.send("Hello ", ZMQ.SNDMORE);
			socket.send("ZMQ ", ZMQ.SNDMORE);
			socket.send("End", 0);
		}
	}

	public static void main(String[] args) {
		MultipartMessageServer.run();
	}
}

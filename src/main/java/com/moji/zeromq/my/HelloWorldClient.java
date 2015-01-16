package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class HelloWorldClient {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket request = cxt.socket(ZMQ.REQ);
		request.connect("tcp://127.0.0.1:5555");
		
		while(! Thread.currentThread().isInterrupted()){
			try {
				Thread.sleep(1000);
				request.send("Hello");
				System.out.println("Send Hello");
				System.out.println("Received "+request.recvStr());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		request.close();
		cxt.term();
	}
	public static void main(String[] args) {
		HelloWorldClient.run();
	}
}

package com.moji.zeromq.my;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class HelloWorldServer {
	public static void run(){
		Context cxt = ZMQ.context(1);
		Socket responder = cxt.socket(ZMQ.REP);
		responder.bind("tcp://*:5555");
		
		while(! Thread.currentThread().isInterrupted()){
			System.out.println("Received "+responder.recvStr());
			
			try {
				Thread.sleep(1000);
				responder.send("World");
				System.out.println("Send World");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		responder.close();
		cxt.term();
	}
	public static void main(String[] args) {
		HelloWorldServer.run();
	}
}

package com.moji.zeromq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 * Clone client model 1
 * @author Danish Shrestha <dshrestha06@gmail.com>
 *
 */
public class clonecli1 {
	private static Map<String, kvsimple> kvMap = new HashMap<String, kvsimple>();
	private static AtomicLong sequence = new AtomicLong();

	public void run() {
		ZContext ctx = new ZContext();
		Socket subscriber = ctx.createSocket(ZMQ.SUB);
		subscriber.connect("tcp://localhost:5556");
		//subscriber.subscribe("".getBytes());//订阅以"0"为前缀的消息,其他前缀的消息将不会收到,相当于消息过滤器
		while (true) {
			kvsimple kvMsg = kvsimple.recv(subscriber);
            if (kvMsg == null)
                break;

            clonecli1.kvMap.put(kvMsg.getKey(), kvMsg);
            System.out.println("receiving " + kvMsg);
            sequence.incrementAndGet();
		}
        ctx.destroy();
	}

	public static void main(String[] args) {
		new clonecli1().run();
	}
}

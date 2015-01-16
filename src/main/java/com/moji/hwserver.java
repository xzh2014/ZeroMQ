//
//  Hello World server in Java
//  Binds REP socket to tcp://*:5555
//  Expects "Hello" from client, replies with "World"
//
package com.moji;
import org.zeromq.ZMQ;

public class hwserver {

    public static void main(String[] args) throws Exception {
        ZMQ.Context context = ZMQ.context(1);//创建一个线程数为1的套接字上下文

        //  Socket to talk to clients
        ZMQ.Socket responder = context.socket(ZMQ.REP);//创建一个REP(应答)模式的套接字
        responder.bind("tcp://*:5555");//绑定地址

        while (!Thread.currentThread().isInterrupted()) {
            // Wait for next request from the client
            byte[] request = responder.recv(0);//从REQ获取消息
            System.out.println("Received "+new String(request));

            // Do some 'work'
            Thread.sleep(1000);

            // Send reply back to client
            String reply = "World";
            responder.send(reply.getBytes(), 0);//发送消息
        }
        responder.close();
        context.term();
    }
}

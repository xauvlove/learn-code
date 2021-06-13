package com.xauv.aio.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Date 2021/06/13 16:38
 * @Author ling yue
 * @Package com.xauv.aio.client
 * @Desc
 * 这个类是用来 将客户端和服务端连接 和 写数据 的
 * 是异步连接，什么时候连接到我们不知道
 * 由于实现了 CompletionHandler
 * 连接成功之后，系统自动调用 completed 方法 我们就知道什么时候连接成功了
 * 连接失败，系统调用 failed 方法 告诉我们连接失败
 */
public class AIOClientHandler implements CompletionHandler<Void, AIOClientHandler>, Runnable {

    private AsynchronousSocketChannel socketChannel;

    private String host;

    private Integer port;

    private CountDownLatch latch;

    public AIOClientHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socketChannel = AsynchronousSocketChannel.open();
            socketChannel.connect(new InetSocketAddress(host, port), this, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        latch = new CountDownLatch(1);
        try {
            latch.await();
            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 由于是异步写 因此需要使用 CompletionHandler 作为写中介
     * 让 CompletionHandler 工具完成异步写过程，我们无需关系底层异步写的实现方式
     * @param message
     */
    public void sendMessage(String message) {
        byte[] bytes = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        socketChannel.write(buffer, buffer, new AIOClientWriteHandler(socketChannel, latch));
    }

    @Override
    public void completed(Void result, AIOClientHandler attachment) {
        System.out.println("已经连接到服务器");
    }

    /**
     * 连接失败
     * 终止该客户端进程
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AIOClientHandler attachment) {
        System.err.println("服务器连接失败");
        latch.countDown();
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exc.printStackTrace();
    }
}

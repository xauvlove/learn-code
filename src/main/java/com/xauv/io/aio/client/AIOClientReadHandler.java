package com.xauv.io.aio.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Date 2021/06/13 17:05
 * @Author ling yue
 * @Package com.xauv.io.aio.client
 * @Desc
 */
public class AIOClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel socketChannel;

    private CountDownLatch latch;

    public AIOClientReadHandler(AsynchronousSocketChannel socketChannel, CountDownLatch latch) {
        this.socketChannel = socketChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        int remaining = attachment.remaining();
        byte[] bytes = new byte[remaining];
        attachment.get(bytes);
        String message = new String(bytes);
        System.out.println("从服务端接收到的数据：" + message);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        latch.countDown();
        System.err.println("读取服务端数据失败");
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

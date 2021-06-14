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
 * @Date 2021/06/13 16:45
 * @Author ling yue
 * @Package com.xauv.io.aio.client
 * @Desc
 */
public class AIOClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel socketChannel;

    private CountDownLatch latch;

    public AIOClientWriteHandler(AsynchronousSocketChannel socketChannel, CountDownLatch latch) {
        this.socketChannel = socketChannel;
        this.latch = latch;
    }

    /**
     *
     * @param result 表示操作系统往网卡里面写入的个数
     * @param attachment
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        // 如果当前网卡缓冲区没写完，则继续写
        // 如果继续写下一部分数据，写完下一部分数据后，系统还会调用到这个方法 判断 attachment.hasRemaining() 直到数据全部写完
        if (attachment.hasRemaining()) {
            socketChannel.write(attachment, attachment, this);
        } else {
            System.out.println("客户端写完成");
            // 读取服务器发给客户端的数据
            // 1.先建立读缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            // 2.读取数据 异步，由于是异步读，因此还是要使用 CompletionHandler 作为中介
            //socketChannel.read(readBuffer, readBuffer, new AIOClientReadHandler(socketChannel, latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        latch.countDown();
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("客户端写失败");
        exc.printStackTrace();
    }
}

package com.xauv.io.aio.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Date 2021/06/13 17:37
 * @Author ling yue
 * @Package com.xauv.io.aio.server
 * @Desc
 */
public class AIOServerAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerHandler> {

    private static Integer acceptCount = 0;

    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServerAcceptHandler(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, AIOServerHandler attachment) {
        acceptCount++;
        System.out.println("已连接成功客户端个数：" + acceptCount);
        // 接收其它客户端连接，由于是异步接收，因此这里不会阻塞  会继续往下执行
        serverSocketChannel.accept(null, new AIOServerAcceptHandler(serverSocketChannel));
        // 连接成功后，读取客户端发来的信息
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer, buffer, new AIOServerReadHandler());
    }

    @Override
    public void failed(Throwable exc, AIOServerHandler attachment) {

    }
}

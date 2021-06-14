package com.xauv.io.aio.server;

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

/**
 * @Date 2021/06/13 17:43
 * @Author ling yue
 * @Package com.xauv.io.aio.server
 * @Desc
 */
public class AIOServerReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel socketChannel;

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if (result <= 0) {
            return;
        }
        attachment.flip();
        int remaining = attachment.remaining();
        byte[] bytes = new byte[remaining];
        attachment.get(bytes);
        String content = new String(bytes);
        System.out.println("从客户端接收到的消息：" + content);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("接收客户端消息失败");
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

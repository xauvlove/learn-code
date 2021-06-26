package com.xauv.socket.io.netty.nio.cases.粘包和半包.解决粘包;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.nio.charset.StandardCharsets;

/**
 * @Date 2021/06/23 21:27
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.粘包和半包
 * @Desc
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String message = "今天天气冷 吃火锅";
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(bytes.length);
            messageProtocol.setContent(bytes);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] bytes = msg.getContent();
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("从服务端接收到的消息，len = " + len + ", content = " + content);
    }
}

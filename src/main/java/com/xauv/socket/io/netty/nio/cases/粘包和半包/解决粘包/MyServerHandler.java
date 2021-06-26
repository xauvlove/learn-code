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
import java.util.UUID;

/**
 * @Date 2021/06/23 21:31
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.粘包和半包
 * @Desc
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        count++;
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到信息 len = " + len + ", content = " + new String(content, StandardCharsets.UTF_8));
        System.out.println("服务端接收到次数 = " + count);

        // 回复消息
        String responseContent = UUID.randomUUID().toString();
        byte[] bytes = responseContent.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        MessageProtocol response = new MessageProtocol();
        response.setLen(length);
        response.setContent(bytes);
        ctx.writeAndFlush(response);
    }
}

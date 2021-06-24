package com.xauv.io.netty.nio.cases.粘包和半包.粘包问题演示;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

/**
 * @Date 2021/06/23 21:31
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.粘包和半包
 * @Desc
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        count++;
        String value = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("服务器接收到的数据: " + value);
        System.out.println("服务器接收到消息量 = " + count);

        // 会送给客户端一个随机 id
        String uuid = UUID.randomUUID().toString();
        ByteBuf byteBuf = Unpooled.copiedBuffer(uuid, CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }
}

package com.xauv.io.netty.nio.simple;

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
import java.nio.charset.StandardCharsets;

/**
 * @Date 2021/06/14 15:33
 * @Author ling yue
 * @Package com.xauv.io.netty.nio
 * @Desc
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 客户端读取到数据后
     *
     * @param channelHandlerContext 每个 channel handler 都有这个对象
     * @param buffer netty 为我们提供的代替 JDK ByteBuffer 的工具类
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf buffer) throws Exception {
        System.out.println("Client received: " + buffer.toString(StandardCharsets.UTF_8));
    }

    /**
     * 客户端被通知 channel 活跃之后 调用这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当 channel 连接建立完成之后 向服务器发送数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty", StandardCharsets.UTF_8));
        System.out.println("发送消息成功");
    }

    /**
     * 异常处理
     * 发生了异常 调用此方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package com.xauv.io.netty.nio;

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
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

/**
 * @Date 2021/06/14 15:56
 * @Author ling yue
 * @Package com.xauv.io.netty.nio
 * @Desc
 */

// 服务器的这个 handler 可能在不同的客户端(channel)之间进行共享
// 服务器这边可以只 new 一个 handler 实例，然后所有的客户端都使用这个 handler，存在线程安全问题
// 这个 handler 的实现必须是线程安全的
// 如果不用这个注解，那么将 handler 加入到 pipeline 时，必须 new 不同实例
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端接收到客户端发送的数据之后
     * 调用此方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("服务端接收到的数据：" + buffer.toString(StandardCharsets.UTF_8));
        ctx.write(buffer);
    }

    /**
     * 服务器读完网络数据之后调用此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 发送空内容 并且flush完成之后关闭连接 ChannelFutureListener.CLOSE
        // 连接关闭之后，客户端 channel 关闭，会自动解除阻塞 完成客户端关闭流程
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 发生异常之后的处理
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

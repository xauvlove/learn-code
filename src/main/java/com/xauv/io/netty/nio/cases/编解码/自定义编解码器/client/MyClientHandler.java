package com.xauv.io.netty.nio.cases.编解码.自定义编解码器.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Date 2021/06/22 22:08
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.编解码.自定义编解码器.client
 * @Desc
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从服务端收到的 Long = " + msg);
    }

    /**
     * 连接成功发送数据
     *
     * 该处理器的前一个 handler 是 MyLongToByteEncoder
     * MyLongToByteEncoder 它的父类是
     * @see io.netty.handler.codec.MessageToByteEncoder
     * 有个方法 io.netty.handler.codec.MessageToByteEncoder#write(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.channel.ChannelPromise)
     * 当 write 被调用时，会判断当前要写的内容是否是需要编码的类型 (当前是 Long)
     * 如果是需要处理的类型 就进行编码，再传给前一个 handler
     * 如果不是，则跳过 encode 直接传递给前一个 handler
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已连接 要发送数据了");
        // 这里要写 123456L 而不是 123456  否则无法完成解析
        ctx.writeAndFlush(123456L);

        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}

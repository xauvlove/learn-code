package com.xauv.io.netty.nio.cases.编解码.自定义编解码器.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @Date 2021/06/22 21:58
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.编解码.自定义编解码器
 * @Desc
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * decode 方法会根据接收到的数据 接收到多次
     * 直到确定没有新的元素被添加到 list
     * 或者是 bytebuf 没有更多的可读字节为止
     * 如果 list 不为空 就会将 list 的内容传递到下一个 ChannelInboundHandler ，这个 ChannelInboundHandler 的方法也会被调用多次
     * 可以理解为 每当往 list 里面加入一个元素。他就会认为 已经有一个应该处理的对象了，
     * 就会把数据向下一个 ChannelInboundHandler 传递 调用下一个 ChannelInboundHandler 的对应方法
     * @param ctx
     * @param in 入站的 ByteBuf
     * @param out 将解码后的数据传给下一个 handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 必须大于等于 8 个字节才能至少读取一个 long
        System.out.println("MyByteToLongDecoder decode is called");
        while (in.readableBytes() >= 8) {
            long readLong = in.readLong();
            out.add(readLong);
        }
    }
}

package com.xauv.io.netty.nio.cases.编解码.自定义编解码器.client;

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
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Date 2021/06/22 22:08
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.编解码.自定义编解码器.client
 * @Desc
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode is called");
        System.out.println("msg = " + msg);
        out.writeLong(msg);
    }
}

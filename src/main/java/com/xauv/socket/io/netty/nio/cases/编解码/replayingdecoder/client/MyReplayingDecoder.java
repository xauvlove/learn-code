package com.xauv.socket.io.netty.nio.cases.编解码.replayingdecoder.client;

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
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/**
 * @Date 2021/06/23 21:05
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.编解码.replayingdecoder.client
 * @Desc
 */
public class MyReplayingDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyReplayingDecoder decode is called");
        // 无需判断 in.readableBytes() > 0
        // 无需判断是否有数据足够读取，内部帮我们做掉了
        out.add(in.readLong());
    }
}

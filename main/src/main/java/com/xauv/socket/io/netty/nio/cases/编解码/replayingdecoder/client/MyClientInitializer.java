package com.xauv.socket.io.netty.nio.cases.编解码.replayingdecoder.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Date 2021/06/22 22:06
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.编解码.自定义编解码器
 * @Desc
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 加一个出站 handler 对数据编码
        pipeline.addLast(new MyLongToByteEncoder());
        // 加解码器
        pipeline.addLast(new MyReplayingDecoder());
        // 加入一个自定义 handler 处理业务
        pipeline.addLast(new MyClientHandler());
    }
}

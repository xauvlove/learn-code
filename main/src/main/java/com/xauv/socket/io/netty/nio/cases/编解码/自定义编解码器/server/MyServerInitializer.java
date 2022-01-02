package com.xauv.socket.io.netty.nio.cases.编解码.自定义编解码器.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.socket.io.netty.nio.cases.编解码.自定义编解码器.client.MyLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Date 2021/06/22 21:56
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.编解码.自定义编解码器
 * @Desc
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个入站 handler 进行解码
        pipeline.addLast(new MyByteToLongDecoder());
        // 加编码器
        pipeline.addLast(new MyLongToByteEncoder());
        // 加一个入站 业务处理器
        pipeline.addLast(new MyServerHandler());
    }
}

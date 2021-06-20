package com.xauv.io.netty.nio.cases.与浏览器交互.netty.http;

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
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Date 2021/06/20 12:26
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.与浏览器交互.netty.http
 * @Desc
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器

        ChannelPipeline pipeline = ch.pipeline();

        // 加入解码处理器 netty 提供的 http server 解码器和编码器
        // HttpServerCodec 既是编码器又是解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        // 增加一个自定义处理器
        pipeline.addLast("MyHttpServerHandler", new HttpServerHandler());
    }
}

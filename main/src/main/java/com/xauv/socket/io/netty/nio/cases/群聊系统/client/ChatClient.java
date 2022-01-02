package com.xauv.socket.io.netty.nio.cases.群聊系统.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.SneakyThrows;

import java.util.Scanner;

/**
 * @Date 2021/06/20 15:39
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.群聊系统.client
 * @Desc
 */
public class ChatClient {

    @SneakyThrows
    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(eventLoopGroup)
                    // 设置客户端通道实现类
                    .channel(NioSocketChannel.class)
                    .remoteAddress("localhost", 9200)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringDecoder());
                            ch.pipeline().addLast(new ChatClientHandler());
                        }
                    });

            System.out.println("客户端已经准备连接了...");
            ChannelFuture sync = bootstrap.connect().sync();
            Channel channel = sync.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.nextLine();
                // 通过 channel 发送信息到服务器
                channel.writeAndFlush(message);
            }
            channel.closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

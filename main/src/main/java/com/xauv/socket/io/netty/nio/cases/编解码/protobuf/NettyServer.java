package com.xauv.socket.io.netty.nio.cases.编解码.protobuf;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @Date 2021/06/19 21:19
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.simple.netty.nio.cases
 * @Desc
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 创建通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 需要指定对哪种信息进行解码
                            channel.pipeline().addLast("decoder", new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                            channel.pipeline().addLast(new NettyServerHandler2());
                        }
                    });
            System.out.println("服务器已经初始化完成");
            ChannelFuture sync = serverBootstrap.bind(9100).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

package com.xauv.io.netty.nio.cases.心跳检测机制;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.io.netty.nio.cases.与浏览器交互.netty.http.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/06/20 16:19
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.心跳检测机制
 * @Desc
 */
public class HeartBeatServer {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 添加一个日志处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入 netty 提供的 IdleStateHandler
                            // IdleStateHandler 是处理空闲状态的处理器
                            // readerIdleTime : 表示多久没有读到客户端发送的数据，就会发送一个心跳检测包，检测是否还是连接的状态
                            // writerIdleTime : 表示多长时间没有写数据给客户端，同样会发送检测包
                            // allIdleTime : 表示多长时间 既没有读也没有写，发送检测包
                            ch.pipeline().addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            // IdleStateHandler 只是发送检测包，我们需要自己定义发生了心跳检测时间该如何处理的逻辑
                            // 当 IdleStateEvent 事件触发后，就会传递给 pipeline 的下一个 handler 去处理，
                            // 通过回调下一个 handler 的 userEventTriggered，在该方法中处理 IdleStateEvent
                            ch.pipeline().addLast(new HeartBeatServerHandler());
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

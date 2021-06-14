package com.xauv.io.netty.nio;

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
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

/**
 * @Date 2021/06/14 15:55
 * @Author ling yue
 * @Package com.xauv.io.netty.nio
 * @Desc
 */
public class EchoServer {

    private Integer port;

    public EchoServer(Integer port) {
        this.port = port;
    }

    @SneakyThrows
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 服务端启动必备
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group);
            // 指明使用 NIO 进行网络通讯
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 指明服务器在哪个端口进行监听
            serverBootstrap.localAddress(port);
            // 接收到连接请求，需要启动一个 socket 通信，也就是 channel（在NIO这种 socket 通信里，一般都是 SocketChannel 包装的）
            // 每个 channel 有自己的事件 handler
            EchoServerHandler echoServerHandler = new EchoServerHandler();
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    // 在 netty 中，一系列的事件全都在 pipeline 中排列，需要将事件加入到 pipeline 中
                    channel.pipeline().addLast(echoServerHandler);
                }
            });
            // 阻塞绑定
            ChannelFuture sync = serverBootstrap.bind().sync();
            // 阻塞 直到 channel 关闭
            sync.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        System.out.println("服务端启动完成");
        EchoServer server = new EchoServer(8004);
        server.start();
    }
}

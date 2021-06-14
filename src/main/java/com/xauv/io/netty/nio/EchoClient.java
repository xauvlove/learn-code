package com.xauv.io.netty.nio;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import java.net.InetSocketAddress;

/**
 * @Date 2021/06/14 15:34
 * @Author ling yue
 * @Package com.xauv.io.netty.nio
 * @Desc
 */
public class EchoClient {

    private String host;

    private Integer port;

    public EchoClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @SneakyThrows
    public void start() {
        // 类似于线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端启动必备
            Bootstrap bootstrap = new Bootstrap();
            // 把线程组交给启动类
            bootstrap.group(group);
            // 指明使用 NIO 进行网络通讯
            bootstrap.channel(NioSocketChannel.class);
            // 配置网络服务器端口地址
            bootstrap.remoteAddress(new InetSocketAddress(host, port));
            // handler 是客户端与服务端交互的媒介
            bootstrap.handler(new EchoClientHandler());
            // 连接服务器 阻塞连接过程直到连接完成
            ChannelFuture sync = bootstrap.connect().sync();
            // 只要通道不关闭 应用程序就会一直阻塞
            sync.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        System.out.println("客户端启动完成");
        EchoClient client = new EchoClient("localhost", 8004);
        client.start();
    }
}

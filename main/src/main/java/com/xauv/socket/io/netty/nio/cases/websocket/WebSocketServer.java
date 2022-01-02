package com.xauv.socket.io.netty.nio.cases.websocket;

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
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.SneakyThrows;

/**
 * @Date 2021/06/20 18:07
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.websocket
 * @Desc
 */
public class WebSocketServer {

    @SneakyThrows
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 创建通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 由于是基于 http 协议，因此增加 http 解码编码器
                            channel.pipeline().addLast(new HttpServerCodec());
                            // 是以块方式写 增加 ChunkedWriteHandler
                            channel.pipeline().addLast(new ChunkedWriteHandler());
                            // http 数据在传输过程中是分段的，HttpObjectAggregator 可以将多个 段 聚合起来
                            // 这就是为什么 浏览器 发送大量数据时，可能会发出多个 http 请求
                            channel.pipeline().addLast(new HttpObjectAggregator(8192));
                            // 对于 websocket 数据，是以 帧(frame) 的形式传递的
                            // 可以看到 WebsocketFrame 下面有 6 个子类
                            // 浏览器请求时，形式为： ws://localhost:9201/hello  以 ws 开头，hello 是请求路径
                            // WebSocketServerProtocolHandler 可以解析 ws 开头的请求
                            // WebSocketServerProtocolHandler 的核心功能是将 http 协议升级为 ws 协议，保持长连接
                            // 升级协议 是通过状态码 101
                            // 首先 浏览器发送 http 协议(http://localhost:~~)，然后服务器返回状态码 101 告诉他需要进行协议升级
                            // 浏览器再次发送 ws 协议(ws://localhost:~~)，请求头新增 connection:upgrade， upgrade:websocket
                            channel.pipeline().addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 自定义 handler 处理业务
                            channel.pipeline().addLast(new WebSocketServerFrameHandler());
                        }
                    });
            System.out.println("服务器已经初始化完成");
            ChannelFuture sync = serverBootstrap.bind(9201).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

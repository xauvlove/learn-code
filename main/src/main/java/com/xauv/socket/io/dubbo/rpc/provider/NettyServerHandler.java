package com.xauv.socket.io.dubbo.rpc.provider;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.socket.io.dubbo.rpc.client.ClientBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Date 2021/06/26 10:43
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.provider
 * @Desc
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取客户端发送的消息 并调用对应
        System.out.println("服务端收到消息：" + msg);
        // 客户端在调用服务器的 api 时，我们需要定义一个协议
        // 我们要求每次发消息 都必须以某个字符串打头
        String content = msg.toString();
        if (!content.startsWith(ClientBootstrap.protocolHead)) {
           return;
        }
        String efficientMessage = content.substring(content.lastIndexOf("#") + 1);
        HelloServiceImpl helloService = new HelloServiceImpl();
        String hello = helloService.hello(efficientMessage);
        ctx.writeAndFlush(hello);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }
}

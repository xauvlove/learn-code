package com.xauv.io.netty.nio.cases.与浏览器交互.netty.http;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Date 2021/06/20 12:23
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases.简单netty应用.cases.与浏览器交互.netty.http
 * @Desc
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取数据时触发该方法
     * @param ctx
     * @param msg HttpObject 是客户端与服务端交互时的处理类型. 客户端与服务端交互时的封装类型
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断 msg 是不是一个 Http Request 类型
        if (!(msg instanceof HttpRequest)) {
            System.out.println("非 http 请求类型");
            return;
        }
        System.out.println(msg.getClass());
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());

        // 获取到 uri 过滤特定资源 不做处理
        HttpRequest httpRequest = (HttpRequest) msg;
        URI uri = new URI(httpRequest.uri());
        // /favicon.ico 是浏览器自动发出的图标请求 用于获取资源图标
        if ("/favicon.ico".equalsIgnoreCase(uri.getPath())) {
            System.out.println("请求 favicon 不做响应");
            return;
        }

        // 回复给浏览器信息，回复信息格式需要满足 http 协议规定格式
        ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);
        // 构造 http 的响应
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        // 响应头
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        // 返回 response
        ctx.writeAndFlush(response);
    }
}

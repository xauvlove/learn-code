package com.xauv.socket.io.netty.nio.cases.群聊系统.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Date 2021/06/20 15:19
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.群聊系统
 * @Desc
 */
public class ChatServerMessageHandler extends SimpleChannelInboundHandler<String> {

    // channel 组，netty 已经帮我们封装好了一个组，无需自己 new、管理 一个 ArrayList
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 消息转发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        // 转发消息时，不能转发给自己，因此不能使用 ChannelGroup 的全部转发功能。需要 自己循环
        for (Channel c : channels) {
            // 当前通道不是发送者通道时，进行转发
            if (c != channel) {
                String content = "用户[" + channel.remoteAddress()+"]" + "说：" + msg;
                ctx.writeAndFlush(Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

package com.xauv.socket.io.netty.nio.cases.群聊系统.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Date 2021/06/20 15:19
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.群聊系统
 * @Desc
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    // channel 组，netty 已经帮我们封装好了一个组，无需自己 new、管理 一个 ArrayList
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 使用 hash map 实现私聊, Map<Id, Channel>
    //public static Map<Long, Channel> channelMap = new HashMap<>();

    /**
     * handler 首次加入调用
     * 表示一旦连接建立，立即执行该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 将当前 channel 加入
        Channel channel = ctx.channel();
        channels.add(channel);
        //channelMap.put(727788947L, channel);
        // 将这个用户上线消息 推送给其他在线的客户端
        channels.writeAndFlush("客户端 " + channel.remoteAddress() + " 加入聊天");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 无需 remove，当 handler 被移除触发此方法时，ChannelGroup 会自动移除 channel
        //channels.remove(channel);
        channels.writeAndFlush("客户端 " + channel.remoteAddress() + " 离开聊天");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }

    /**
     * 消息转发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("====");
        Channel channel = ctx.channel();
        // 转发消息时，不能转发给自己，因此不能使用 ChannelGroup 的全部转发功能。需要 自己循环
        for (Channel c : channels) {
            // 当前通道不是发送者通道时，进行转发
            if (c != channel) {
                String content = "用户[" + channel.remoteAddress()+"]" + "说：" + msg;
                c.writeAndFlush(content);
            }
        }
    }
}

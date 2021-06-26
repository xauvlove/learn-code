package com.xauv.socket.io.netty.nio.cases.心跳检测机制;

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
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import java.net.SocketAddress;

/**
 * @Date 2021/06/20 16:32
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.心跳检测机制
 * @Desc
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 心跳检测事件一旦发生 就会不停地发送心跳包来检测
     * ？？？直到有读写事件发生？？？
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 如果不是 idle 事件，不处理
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }

        // 强制转型
        IdleStateEvent event = (IdleStateEvent) evt;
        String eventType = "";
        switch (event.state()) {
            case READER_IDLE: {
                eventType = "读空闲";
                break;
            }
            case WRITER_IDLE: {
                eventType = "写空闲";
                break;
            }
            case ALL_IDLE: {
                eventType = "读写空闲";
                break;
            }
        }
        Channel channel = ctx.channel();
        SocketAddress socketAddress = channel.remoteAddress();
        System.out.println("客户端[" + socketAddress + "]" + eventType);

        // 如果发生空闲了 我们直接关闭通道，那么心跳检测只发送一次
        //ctx.close();
    }
}

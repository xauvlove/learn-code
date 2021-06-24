package com.xauv.io.netty.nio.cases.编解码.protobuf;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/06/19 21:38
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases
 * @Desc
 * ChannelInboundHandlerAdapter 是一个入站适配器
 * 我们自定义一个 handler 需要继承一个 netty 规定好的适配器
 * 继承了之后 我们的 handler 才能入站
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的消息
     * @param ctx 上下文，我们可以拿到很多信息
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取从客户端发送的 StudentPOJO.Student
        StudentPOJO.Student student = (StudentPOJO.Student) msg;
        System.out.println("客户端发送的数据：" + "id = " + student.getId() + " name = " + student.getName());
    }



    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入缓冲区并写入通道
        // 一般来讲 我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));
    }

    /**
     * 发生异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生了异常后 一般关闭通道
        ctx.close();
    }

    private void submitScheduleTask(ChannelHandlerContext ctx) {
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("很复杂的业务C执行完成了 -- 定时任务", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS);
    }

    private void submitTask(ChannelHandlerContext ctx) {
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("很复杂的业务A执行完成了", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 这里是将任务加入到 task queue 中，任务按照次序依次执行 因此，这个任务需要等待 20 秒才能执行发送
        // 因为 每个线程具有一个唯一 task queue，每个线程的 task queue 是不同的，因此如果任务进入的是同一个队列，那么这些任务只能按照加入次序 依次执行
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("很复杂的业务B执行完成了", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

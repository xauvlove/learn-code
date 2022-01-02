package com.xauv.socket.io.dubbo.rpc.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Date 2021/06/26 10:59
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.client
 * @Desc
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;

    // 调用服务方法返回的结果
    private String result;

    // 客户端调用方法时传入的参数
    private String param;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        // 唤醒等待的线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 被代理对象调用 -> 发送数据给服务器 -> 等待被唤醒 -> 返回结果
     * 这里和 channelRead 必须加同步
     * 由于 call() 是异步的，
     * 在不加 synchronized 同步的时候
     * 如果同时调用两次 call()，分别为 call1 call2
     * 那么不知道谁先得到返回，如果 call1先返回，那么 channelRead 读到 call1 的返回结果
     * 如果 call2 再返回 channelRead 读到 call2 的返回结果
     *
     * 如果 call1 和 call2 同时调用，并且同时返回，
     * 就有可能 call1 的结果返回给了 call2 的调用线程
     * 也就是说，call1 的发起者接收到了 call2 的数据
     * call2 的发起者接收到了 call1 的数据，这肯定是不允许的
     *
     * 因此，在 call() 和 channelRead() 加一个同步，就能保证 每次只能是同一个线程拿到 handler 这把锁
     * 才能执行 call() 和 channelRead()，
     * 如果同时发起 call1() 和 call2()，那么只能是 call1 call2() 其中一个能够完整地执行 call() -> channelRead() 这个流程
     * 另外一个线程只能等待 上一个线程完全执行完毕，才能继续执行 call() -> channelRead() 这个流程
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        // 等待 channelRead 被 netty 执行
        wait();
        return result;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

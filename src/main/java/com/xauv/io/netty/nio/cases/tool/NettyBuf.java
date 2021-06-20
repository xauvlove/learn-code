package com.xauv.io.netty.nio.cases.tool;

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
import io.netty.util.CharsetUtil;

/**
 * @Date 2021/06/20 14:41
 * @Author ling yue
 * @Package com.xauv.io.netty.nio.cases
 * @Desc
 */
public class NettyBuf {

    public static void main(String[] args) {

        /*----------------------- buf 区域 -----------------------*/
        // netty Buf 不需要 flip 因为其中自己维护了 readIndex writeIndex
        // buf 中有三个字段：readIndex writeIndex capacity
        // 三个字段将 buf 分为三个部分区域
        // 0 - readIndex 表示已经读过的区域
        // readIndex - writeIndex 表示可读的，剩余未读的区域
        // writeIndex - capacity 表示可写的区域
        ByteBuf byteBuf = Unpooled.buffer(10);

        // 如果写入数据超过 buf 的容量，那么会自动扩容
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }

        for (int i = 0; i < byteBuf.capacity(); i++) {
            // byteBuf.readByte() 导致 readIndex 自增
            System.out.println(byteBuf.readByte());
        }

        /*------------------------ 相关 api ----------------------*/
        ByteBuf buf = Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8);
        if (buf.hasArray()) {
            byte[] array = buf.array();
            System.out.println(new String(array, CharsetUtil.UTF_8).trim());
        }
    }
}

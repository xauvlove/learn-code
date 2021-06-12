package com.xauv.nio;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @Date 2021/06/12 12:05
 * @Author ling yue
 * @Package com.xauv.nio
 * @Desc
 */
public class Buffer {

    public static void main(String[] args) throws Exception {

    }


    /**
     * 文件内存直接映射
     * 通过修改内存 直接对文件进行操作
     * 把缓冲区跟一个 文件系统进行关联
     * 缓冲区内容修改后，文件内容会跟着变
     */
    public static void learnBufferFileMap() throws Exception {
        // 读一个文件
        RandomAccessFile randomAccess = new RandomAccessFile("D:\\test.txt", "rw");
        FileChannel channel = randomAccess.getChannel();
        // 对文件进行内存 磁盘映射，通过操作内存 直接对磁盘文件进行数据修改
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 50);

        // 执行完下面两行 test.txt 文件变成了 ： css                                              c
        // 这里有很多空格 由于我们下面是对 文件中字节位置 进行操作
        map.put((byte) 99);
        map.put(49, (byte) 99);
    }
    /**
     * 文件读到 buffer
     * @throws Exception
     */
    public static void leaningBuffer() throws Exception {
        // 先写一个文件 作数据准备
        FileOutputStream outputStream = new FileOutputStream("D:\\test.txt");
        byte[] bytes = new byte[]{25, 26, 27, 28, 29, 30};
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

        // 把文件内容读到 buffer
        FileInputStream inputStream = new FileInputStream("D:\\test.txt");
        FileChannel channel = inputStream.getChannel();
        // 申请普通 buffer 内存，需经过 jvm 数据拷贝
        ByteBuffer buffer = ByteBuffer.allocate(2);
        // 申请直接内存 读文件不需要 jvm 内存拷贝  实现 《零拷贝》
        //ByteBuffer buffer = ByteBuffer.allocateDirect(15);
        int read = channel.read(buffer);
        System.out.printf("读到了 %s 个字节\n", read);
        System.out.printf("读到的数据：%s\n", Arrays.toString(buffer.array()));
        /*-------printBufferInfo-------*/
        printBufferInfo(buffer);
        // 先进行锁定，如果不锁定，buffer.hasRemaining = false
        buffer.flip();
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.printf("循环读数据 b = %s\n", b);
        }
        /*-------printBufferInfo-------*/
        printBufferInfo(buffer);
        // 清空 buffer
        buffer.clear();
        /*-------printBufferInfo-------*/
        printBufferInfo(buffer);
    }

    public static void printBufferInfo(ByteBuffer buffer) {
        System.out.printf("capacity: %s, position: %s, limit: %s\n", buffer.capacity(), buffer.position(), buffer.limit());
    }
}

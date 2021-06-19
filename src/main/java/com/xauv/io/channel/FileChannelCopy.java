package com.xauv.io.channel;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @Date 2021/06/17 22:31
 * @Author ling yue
 * @Package com.xauv.io.channel
 * @Desc
 * 零拷贝 复制文件
 */
public class FileChannelCopy {

    @SneakyThrows
    public static void main(String[] args) {
        FileInputStream fileInputStream = new FileInputStream("d:\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\test-copy.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        // 零拷贝
        //inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        // 关闭流
        fileInputStream.close();
        fileOutputStream.close();
        inChannel.close();
        outChannel.close();
    }
}

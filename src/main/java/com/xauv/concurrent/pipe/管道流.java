package com.xauv.concurrent.pipe;

/**
 * /\   /\             /\.__
 * ___  __)/___)/  __ _____  _)/|  |   _______  __ ____
 * \  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \
 * >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/
 * /__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
 * \/     \/                                    \/
 */

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Date 2021/04/21 21:29
 * @Author ling yue
 * @Package com.xauv.concurrent.pipe
 * @Desc
 */
public class 管道流 {

    public static void main(String[] args) throws Exception {

        PipedInputStream inputStream = new PipedInputStream();

        PipedOutputStream outputStream = new PipedOutputStream();

        inputStream.connect(outputStream);

        Thread t1 = new Thread(() -> {
            try {
                int length = 0;
                byte[] bytes = new byte[10];
                while ((length = inputStream.read(bytes)) > 0) {
                    byte[] bs = new byte[length];
                    System.arraycopy(bytes, 0, bs, 0, length);
                    String content = new String(bs);
                    System.out.println(content);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 555; i < 569; i++) {
                    outputStream.write((i + " ").getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

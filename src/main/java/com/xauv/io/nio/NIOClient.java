package com.xauv.io.nio;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/06/14 14:05
 * @Author ling yue
 * @Package com.xauv.io.nio
 * @Desc
 */
public class NIOClient {

    public static void main(String[] args) {
        NIOClientHandler nioClientHandler = new NIOClientHandler("localhost", 8003);
        Thread t = new Thread(nioClientHandler);
        t.start();
    }
}

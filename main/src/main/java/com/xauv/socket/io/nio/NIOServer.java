package com.xauv.socket.io.nio;

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
 * @Package com.xauv.socket.io.nio
 * @Desc
 */
public class NIOServer {

    public static void main(String[] args) {
        NIOServerHandler nioServerHandler = new NIOServerHandler(8003);
        Thread t = new Thread(nioServerHandler);
        t.start();
    }
}

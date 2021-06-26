package com.xauv.socket.io.aio.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.io.IOException;

/**
 * @Date 2021/06/13 16:46
 * @Author ling yue
 * @Package com.xauv.socket.io.aio.client
 * @Desc
 */
public class AOIClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        AIOClientHandler clientConnectHandler = new AIOClientHandler("localhost", 8002);
        new Thread(clientConnectHandler).start();
        Thread.sleep(3000);
        while (System.in.read() != 0) {
            clientConnectHandler.sendMessage("first message");
        }
    }
}

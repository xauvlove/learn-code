package com.xauv.aio.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/06/13 17:29
 * @Author ling yue
 * @Package com.xauv.aio.server
 * @Desc
 */
public class AIOServer {

    public static void main(String[] args) {
        AIOServerHandler aioServerHandler = new AIOServerHandler(8002);
        aioServerHandler.init();
        new Thread(aioServerHandler).start();
    }
}

package com.xauv.socket.io.dubbo.rpc.provider;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/06/26 10:35
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.provider
 * @Desc
 */
public class ServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer(8000);
    }
}

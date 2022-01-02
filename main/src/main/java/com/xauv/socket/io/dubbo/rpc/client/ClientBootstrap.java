package com.xauv.socket.io.dubbo.rpc.client;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.socket.io.dubbo.rpc.api.HelloService;

/**
 * @Date 2021/06/26 11:28
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.client
 * @Desc
 */
public class ClientBootstrap {

    public static final String protocolHead = "HelloService#hello#";

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        HelloService bean = (HelloService) nettyClient.getBean(HelloService.class, protocolHead);
        String hello = bean.hello("你好 dubbo ~");
        System.out.println("调用结果：" + hello);
    }
}

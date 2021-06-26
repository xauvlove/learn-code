package com.xauv.socket.io.dubbo.rpc.api;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/06/26 10:29
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.interfaces
 * @Desc
 * 服务提供方和服务消费方都需要的
 */
public interface HelloService {

    String hello(String msg);
}

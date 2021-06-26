package com.xauv.dubbo.基本使用;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.cluster.LoadBalance;

/**
 * @Date 2021/06/26 14:24
 * @Author ling yue
 * @Package com.xauv.dubbo
 * @Desc
 */
public class DubboClient {

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-client");
        RegistryConfig registryConfig = new RegistryConfig("multicast://224.222.222.222:9333");
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserService.class);
        // 默认随机负载均衡 现在设置为轮询
        referenceConfig.setLoadbalance("roundrobin");
        //referenceConfig.setUrl("dubbo://127.0.0.1:9919/com.xauv.dubbo.基本使用.UserService");
        UserService userService = referenceConfig.get();
        System.out.println(userService.hello());
    }
}

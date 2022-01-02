package com.xauv.dubbo.基本使用;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.SneakyThrows;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * @Date 2021/06/26 14:15
 * @Author ling yue
 * @Package com.xauv.dubbo
 * @Desc
 */
public class DubboServer {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-server");
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        // 默认是 20880
        protocolConfig.setPort(9919);
        // 不使用注册中心
        //RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);

        // 使用组网广播注册中心<只有局域网能用> IP是虚拟的 随便设置
        RegistryConfig registryConfig = new RegistryConfig("multicast://224.222.222.222:9333");
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        // 暴露服务
        serviceConfig.export();
        System.out.println("服务器启动成功");
        System.in.read();
    }
}

package com.xauv.dubbo.combinespring.start;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.dubbo.combinespring.service.UserService;
import lombok.SneakyThrows;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Date 2021/06/26 15:04
 * @Author ling yue
 * @Package com.xauv.dubbo.combinespring
 * @Desc
 *
 * 解析注册到 zookeeper 上的服务
 * String decode = URLDecoder.decode("[dubbo%3A%2F%2F192.168.31.110%3A20880%2Fcom.xauv.dubbo.combinespring.UserService" +
 *                         "%3Fanyhost%3Dtrue%26application%3Dspring-dubbo-server%26bean.name%3Dcom.xauv.dubbo.combinespring.UserService%26deprecated%3Dfalse%26dubbo%3D2.0.2%26 " +
 *                         "dynamic%3Dtrue%26generic%3Dfalse%26interface%3Dcom.xauv.dubbo.combinespring.UserService%26methods%3Dhello%26pid%3D12004%26release%3D2.7.4.1%26side%3D " +
 *                 "provider%26timestamp%3D1625318016016]", "UTF8");
 * System.out.println(decode);
 *
 * 解析结果：
 * [dubbo://192.168.31.110:20880/com.xauv.dubbo.combinespring.UserService?anyhost=true&application=spring-dubbo-server&bean.name=com.xauv.dubbo.combinespring.UserService
 * &deprecated=false&dubbo=2.0.2& dynamic=true&generic=false&interface=com.xauv.dubbo.combinespring.UserService&methods=hello&pid=12004&release=2.7.4.1
 * &side= provider&timestamp=1625318016016]
 */
public class SpringDubboClient {

    @SneakyThrows
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("consumer.xml");
        context.refresh();
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.hello());
    }
}

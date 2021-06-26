package com.xauv.dubbo.combinespring;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.SneakyThrows;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Date 2021/06/26 15:04
 * @Author ling yue
 * @Package com.xauv.dubbo.combinespring
 * @Desc
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

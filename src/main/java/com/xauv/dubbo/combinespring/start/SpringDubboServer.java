package com.xauv.dubbo.combinespring.start;

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
 * @Date 2021/06/26 14:48
 * @Author ling yue
 * @Package com.xauv.dubbo.集成spring
 * @Desc
 */
public class SpringDubboServer {

    @SneakyThrows
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("provider.xml");
        context.refresh();
        System.out.println("服务器启动成功");
        int read = System.in.read();
    }
}

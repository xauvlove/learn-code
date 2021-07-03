package com.xauv.dubbo.spi;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @Date 2021/07/03 20:30
 * @Author ling yue
 * @Package com.xauv.dubbo.spi
 * @Desc
 */
public class SPICase {

    public static void main(String[] args) {
        // dubbo 拥有自己的 loader
        ExtensionLoader<MyService> loader = ExtensionLoader.getExtensionLoader(MyService.class);
        // 默认扩展类实现
        MyService defaultExtension = loader.getDefaultExtension();
        MyService serviceA = loader.getExtension("serviceA");
        MyService serviceB = loader.getExtension("serviceB");

        System.out.println(defaultExtension.say());
        System.out.println(serviceA.say());
        System.out.println(serviceB.say());
    }
}

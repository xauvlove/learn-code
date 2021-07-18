package com.xauv.dubbo.spi;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;

import java.util.List;
import java.util.Set;

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
        MyService adaptiveExtension = loader.getAdaptiveExtension();
        System.out.println(adaptiveExtension.say());
        // 默认扩展类实现
        MyService defaultExtension = loader.getDefaultExtension();
        MyService serviceA = loader.getExtension("serviceA");
        MyService serviceB = loader.getExtension("serviceB");

        System.out.println(defaultExtension.say());
        System.out.println(serviceA.say());
        System.out.println(serviceB.say());

        URL url = new URL("", "", 0);
        //url = url.addParameter("cache", "cache");
        ExtensionLoader<Filter> extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);
        List<Filter> cache = extensionLoader.getActivateExtension(url, "cache");

        System.out.println();
    }
}

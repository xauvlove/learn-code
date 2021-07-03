package com.xauv.dubbo.spi;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import org.apache.dubbo.common.extension.SPI;

/**
 * @Date 2021/07/03 20:31
 * @Author ling yue
 * @Package com.xauv.dubbo.spi
 * @Desc
 */
public class MyServiceImplA implements MyService{
    @Override
    public String say() {
        return "MyServiceImplA";
    }
}

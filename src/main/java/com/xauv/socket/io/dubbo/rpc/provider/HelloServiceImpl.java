package com.xauv.socket.io.dubbo.rpc.provider;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.socket.io.dubbo.rpc.api.HelloService;
import org.apache.commons.lang3.StringUtils;

/**
 * @Date 2021/06/26 10:30
 * @Author ling yue
 * @Package com.xauv.socket.io.dubbo.rpc.provider
 * @Desc
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息：" + msg);
        // 根据 msg 返回不同的结果
        if (StringUtils.isNotBlank(msg)) {
            return "hello 客户端，我已经收到了消息：message = " + msg;
        } else {
            return "hello 客户端，我已经收到了你的消息";
        }
    }
}

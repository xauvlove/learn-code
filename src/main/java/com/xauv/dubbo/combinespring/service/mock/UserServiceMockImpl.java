package com.xauv.dubbo.combinespring.service.mock;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.dubbo.combinespring.service.UserService;

/**
 * @Date 2021/07/03 21:21
 * @Author ling yue
 * @Package com.xauv.dubbo.combinespring.service.mock
 * @Desc
 */
public class UserServiceMockImpl implements UserService {

    @Override
    public String hello() {
        return "mock";
    }
}

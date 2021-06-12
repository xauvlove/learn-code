package com.xauv.tomcat.dto;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.Data;

/**
 * @Date 2021/06/12 13:40
 * @Author ling yue
 * @Package com.xauv.tomcat.server.dto
 * @Desc
 */
@Data
public class MyRequest {

    private String path;

    private String method;
}

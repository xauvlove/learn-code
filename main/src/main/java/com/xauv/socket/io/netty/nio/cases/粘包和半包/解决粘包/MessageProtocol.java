package com.xauv.socket.io.netty.nio.cases.粘包和半包.解决粘包;

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
 * @Date 2021/06/23 21:49
 * @Author ling yue
 * @Package com.xauv.socket.io.netty.nio.cases.粘包和半包.解决粘包
 * @Desc
 *
 * 协议包
 */
@Data
public class MessageProtocol {

    // 长度
    private int len;

    // 内容
    private byte[] content;
}

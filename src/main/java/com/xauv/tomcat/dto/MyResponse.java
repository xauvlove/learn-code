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

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Date 2021/06/12 13:40
 * @Author ling yue
 * @Package com.xauv.tomcat.dto
 * @Desc
 */
@Data
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeHttpResponseContent(String content) {
        String head = "HTTP/1.1 200 OK\n";
        String type = "Content-Type: text/html;\n\r\n";
        try {
            outputStream.write((head + type + content).getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

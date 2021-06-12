package com.xauv.tomcat.server;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.tomcat.dto.MyRequest;
import com.xauv.tomcat.dto.MyResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Date 2021/06/12 13:42
 * @Author ling yue
 * @Package com.xauv.tomcat.server
 * @Desc
 */
public class Handler {

    public void handle(InputStream inputStream, OutputStream outputStream) throws Exception {
        byte[] bytes = new byte[1024];
        int read = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((read = inputStream.read(bytes)) > 0) {
            stringBuilder.append(new String(bytes, 0, read));
        }
        String content = stringBuilder.toString();
        String[] split = content.split("\\n");


        String modes = split[0];
        String method = modes.split(" ")[0];
        String path = modes.split(" ")[1].replace("/", "");
        MyServlet servlet = new MyServlet();

        MyRequest request = new MyRequest();
        request.setMethod(method);
        request.setPath(path);
        MyResponse response = new MyResponse(outputStream);

        servlet.service(request, response);
    }
}

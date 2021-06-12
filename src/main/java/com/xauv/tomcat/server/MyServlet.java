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

import java.lang.reflect.Method;

/**
 * @Date 2021/06/12 13:53
 * @Author ling yue
 * @Package com.xauv.tomcat.server
 * @Desc
 */
public class MyServlet {

    public void service(MyRequest request, MyResponse response) {
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public void doGet(MyRequest request, MyResponse response) {
        MyService myService = new MyService();
        String path = request.getPath();
        try {
            Method method = MyService.class.getMethod(path);
            String result = (String) method.invoke(myService);
            response.writeHttpResponseContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest request, MyResponse response) {
        doGet(request, response);
    }
}

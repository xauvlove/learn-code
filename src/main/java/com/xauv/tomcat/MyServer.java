package com.xauv.tomcat;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.tomcat.server.Handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 * @Date 2021/06/12 13:39
 * @Author ling yue
 * @Package com.xauv.tomcat.server
 * @Desc
 * 底层都是用的 socket
 * 接收到 http 请求头 请求行 进行解析
 * 解析后拿到 请求 path 映射到具体的方法进行执行
 * 再通过网络IO 写数据
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket client = serverSocket.accept();
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();
        Handler handler = new Handler();
        handler.handle(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        client.close();
    }
}

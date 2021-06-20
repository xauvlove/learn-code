package com.xauv.io.nio;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Date 2021/06/14 13:24
 * @Author ling yue
 * @Package com.xauv.io.nio
 * @Desc
 */
public class NIOClientHandler implements Runnable {

    private Integer port;

    private String host;

    private Selector selector;

    private SocketChannel socketChannel;

    private Boolean start = false;

    public NIOClientHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
        init();
    }

    @SneakyThrows
    public void init() {
        // 打开 selector
        selector = Selector.open();
        // 打开连接
        socketChannel = SocketChannel.open();
        // 设置为非阻塞 IO
        socketChannel.configureBlocking(false);
        start = true;
    }

    @SneakyThrows
    @Override
    public void run() {
        connect();
        while (start) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                handleInput(selectionKey);
            }
            iterator.remove();
        }
    }

    @SneakyThrows
    private void handleInput(SelectionKey selectionKey) {
        if (selectionKey.isConnectable()) {
            // 连接完成之后 向服务端发送数据
            String content = "xauvlove";
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(wrap);
            System.out.println("已向服务器发送数据");
            return;
        }

        if (selectionKey.isReadable()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int length = channel.read(buffer);
            if (length > 0) {
                buffer.flip();
                byte[] bytes = new byte[length];
                if (buffer.hasRemaining()) {
                    buffer.get(bytes);
                    System.out.println("从服务端获取的数据为：" + new String(bytes));
                }
            } else {
                selectionKey.channel().close();
                selectionKey.cancel();
            }
        }
    }

    @SneakyThrows
    private void connect() {
        // 由于是非阻塞连接方式，因此我们不知道什么时候能够连接成功
        boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
        // 如果不能立即连接成功，则注册连接事件
        if (!connect) {
            try {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      /*  if (!connect) {
            while (!socketChannel.finishConnect()){}
        }*/
       /* // 连接完成之后 向服务端发送数据
        String content = "xauvlove";
        ByteBuffer wrap = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
        socketChannel.write(wrap);
        System.out.println("已向服务器发送数据");*/
    }
}

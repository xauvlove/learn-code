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
import java.nio.channels.*;
import java.util.Set;

/**
 * @Date 2021/06/14 13:46
 * @Author ling yue
 * @Package com.xauv.io.nio
 * @Desc
 */
public class NIOServerHandler implements Runnable {

    private Integer port;

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private Boolean start = false;

    public NIOServerHandler(Integer port) {
        this.port = port;
        build();
    }

    @SneakyThrows
    public void build() {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        // selector 打开，serverSocketChannel 建立后，就开始注册监听 客户端接入事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        start = true;
        System.out.println("服务端已启动成功");
    }

    @SneakyThrows
    @Override
    public void run() {

        while (start) {
            //  selector.select(); 是阻塞的，如果  selector 没有任何事件，那么阻塞在这里
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (!selectionKey.isValid()) {
                    continue;
                }
                handleOptions(selectionKey);
            }
        }
    }

    @SneakyThrows
    private void handleOptions(SelectionKey selectionKey) {
        if (selectionKey.isAcceptable()) {
            handleAcceptOption(selectionKey);
            return;
        }

        if (selectionKey.isReadable()) {
            handleReadOption(selectionKey);
            return;
        }
    }

    @SneakyThrows
    private void handleReadOption(SelectionKey selectionKey) {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int length = channel.read(buffer);
        if (length > 0) {
            buffer.flip();
            byte[] bytes = new byte[length];
            buffer.get(bytes);
            System.out.println("从客户端接收到的消息：" + new String(bytes));
        } else {
            selectionKey.channel().close();
            // 取消注册事件监听
            selectionKey.cancel();
        }
    }

    /**
     * 处理 accept 事件
     * @param selectionKey
     */
    @SneakyThrows
    private void handleAcceptOption(SelectionKey selectionKey) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel accept = serverSocketChannel.accept();
        accept.configureBlocking(false);
        // 接受了一个客户端的接入，那么接下来就应该为这个客户端注册 读事件，以对客户端发送的消息接收
        accept.register(selector, SelectionKey.OP_READ);
    }
}

package com.xauv.socket.io.nio.基于NIO的群聊系统;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Date 2021/06/19 13:57
 * @Author ling yue
 * @Package com.xauv.socket.io.nio.基于NIO的群聊系统
 * @Desc
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private static final int port = 8009;

    @SneakyThrows
    public GroupChatServer() {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @SneakyThrows
    public void listen() {
        while (true) {
            System.out.println("服务端正在等待客户端连接");
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 监听连接事件
                if (selectionKey.isAcceptable()) {
                    handleAccept();
                }

                // 如果监听到读事件，有客户端发送数据
                if (selectionKey.isReadable()) {
                    handleRead(selectionKey);
                }
                iterator.remove();
            }
        }
    }

    @SneakyThrows
    private void handleRead(SelectionKey selectionKey) {
        // 实现消息转发
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int length = channel.read(allocate);
        List<Byte> byteList = new ArrayList<>();
        while (length > 0) {
            allocate.flip();
            for (byte b : allocate.array()) {
                byteList.add(b);
            }
            allocate.clear();
            length = channel.read(allocate);
        }
        Object[] objects = byteList.toArray();
        byte[] bytes = new byte[objects.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (Byte) objects[i];
         }
        String message = new String(bytes);
        System.out.println("服务端收到的消息：" + message);

        // 转发
        forwardMessageToOtherClient(message, channel);
    }

    /**
     * 转发消息  排除自己（发送者客户端）
     * @param message
     */
    private void forwardMessageToOtherClient(String message, SocketChannel excludeChannel) {
        System.out.println("服务器转发消息中...");
        // 全部注册到 selector 的 channel
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey selectionKey : keys) {
            Channel targetChannel = selectionKey.channel();
            if (!(targetChannel instanceof  SocketChannel)) {
                continue;
            }
            if (targetChannel == excludeChannel) {
                continue;
            }
            ByteBuffer allocate = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            SocketChannel writableByteChannel = (SocketChannel) targetChannel;
            try {
                writableByteChannel.write(allocate);
            } catch (IOException e) {
                try {
                    System.out.println(writableByteChannel.getRemoteAddress() + " 离线了");
                    writableByteChannel.close();
                    // 取消事件监听
                    selectionKey.cancel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @SneakyThrows
    private void handleAccept() {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        // 将 客户端 注册到 selector 上，并监听读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.getRemoteAddress() + " 上线了");
    }

    public static void main(String[] args) {

        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}

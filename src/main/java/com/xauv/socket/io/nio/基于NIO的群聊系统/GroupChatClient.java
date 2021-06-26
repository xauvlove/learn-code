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
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Date 2021/06/19 14:40
 * @Author ling yue
 * @Package com.xauv.socket.io.nio.基于NIO的群聊系统
 * @Desc
 */
public class GroupChatClient {

    private final String host = "localhost";

    private final int port = 8009;

    private SocketChannel socketChannel;

    private Selector selector;

    private String userName;

    @SneakyThrows
    public GroupChatClient() {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
        System.out.println("开始连接服务器");
        if (!connect) {
            while (!socketChannel.finishConnect()) {}
        }
        userName = socketChannel.getLocalAddress().toString();
        System.out.println("客户端连接成功 userName: " + userName);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    @SneakyThrows
    public void sendMessage(String message) {
        message = userName + " say: " + message;
        socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
    }

    @SneakyThrows
    private void readMessage() {
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();
        if (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            if (selectionKey.isReadable()) {
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                int read = socketChannel.read(allocate);
                List<Byte> byteList = new ArrayList<>();
                if (read > 0) {
                    for (byte b : allocate.array()) {
                        byteList.add(b);
                    }
                    Byte[] bytes = byteList.toArray(new Byte[0]);
                    System.out.println("其他人发送的消息： " + Arrays.toString(bytes));
                }
            }
            iterator.remove();
        }
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                client.sendMessage(UUID.randomUUID().toString());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(client::readMessage).start();
    }
}

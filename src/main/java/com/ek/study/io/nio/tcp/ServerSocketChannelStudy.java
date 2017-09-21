package com.ek.study.io.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/6
 */
public class ServerSocketChannelStudy {

    private static ExecutorService executors = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(true);
        while (true) {
            SocketChannel accept = serverSocketChannel.accept();
            if (accept == null) {
                System.out.println("no connect come.....");
            } else {
                executors.submit(() -> {
                    ByteBuffer buf = ByteBuffer.allocate(48);
                    System.out.println("connect!");
                    try {
                        while ((accept.read(buf) != -1)) {
                            buf.flip();
                            while (buf.hasRemaining()) {
                                System.out.print((char) buf.get());
                            }
                            buf.clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("connect!");
                });


            }
        }
    }
}


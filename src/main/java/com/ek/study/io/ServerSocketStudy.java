package com.ek.study.io;

import io.netty.bootstrap.ServerBootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/20
 */
public class ServerSocketStudy {

    public static void main(String[] args) throws IOException {
        Server server = new Server("127.0.0.1", 80);
        server.start();
    }

}


class Server {
    private String host;

    private int port;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void start() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(this.host, this.port));

        serverSocketChannel.configureBlocking(true);


        while (true) {

            SocketChannel accept = serverSocketChannel.accept();

            Selector selector = Selector.open();

            accept.register(selector, SelectionKey.OP_ACCEPT);

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap

            new Thread(() -> {
                InputStream inputStream;
                OutputStream outputStream;
                try {
                    System.out.println(accept.toString());
                    inputStream = accept.getInputStream();
                    outputStream = accept.getOutputStream();
                    while (!accept.isClosed()) {
                        System.out.println("wait....");
                        byte[] bytes = new byte[1024];
                        int i = inputStream.read(bytes);

                        System.out.println("******" + i);

                        if (i == -1) {
                            break;
                        }
                        String s = new String(bytes).trim();
                        System.out.println(s);
                        System.out.println(s.trim().length());

                        boolean isStop = s.equals("abcdefg");
                        System.out.println(isStop);
                        outputStream.write("receive".getBytes(Charset.forName("utf-8")));
                        outputStream.flush();
                        if (isStop) {
                            accept.close();
                        }


                    }
                    System.out.println("end normal");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("end");
            }).start();

        }

    }
}

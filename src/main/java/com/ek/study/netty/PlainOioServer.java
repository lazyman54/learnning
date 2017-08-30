package com.ek.study.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/30
 */
public class PlainOioServer {

    public static void main(String[] args) {
        PlainOioServer server = new PlainOioServer();
        try {
            server.server(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                System.out.println("accepted connect from " + clientSocket);
                new Thread(() -> {
                    OutputStream out;
                    try {
                        out = clientSocket.getOutputStream();
                        out.write("hi! \r \n".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

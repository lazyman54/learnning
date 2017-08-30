package com.ek.study.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/6
 */
public class SocketChannelStudy {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

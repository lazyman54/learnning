package com.ek.study.io.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/6
 */
public class DatagramChannelStudy {
    public static void main(String[] args) throws IOException {
        Sender.send();
        //Receive.receive();
    }
}


class Sender {

    public static void send() throws IOException {

        DatagramChannel datagramChannel = DatagramChannel.open();
        ByteBuffer buf = ByteBuffer.allocate(48);

        buf.put("I am lazyman".getBytes());
        buf.flip();

        datagramChannel.send(buf, new InetSocketAddress("127.0.0.1", 8888));
    }
}

class Receive {
    public static void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress("127.0.0.1", 8888));
        ByteBuffer buf = ByteBuffer.allocate(48);
        datagramChannel.receive(buf);

        buf.flip();
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }
    }
}

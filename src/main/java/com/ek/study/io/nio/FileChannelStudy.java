package com.ek.study.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/6
 */
public class FileChannelStudy {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("fileChannel.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.putLong(456L);
        System.out.println(buf);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            System.out.println(buf.getLong());
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println("****************");
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        inChannel.close();
        aFile.close();

    }
}

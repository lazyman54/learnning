package com.ek.study.netty;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/30
 */
public class PlainOioClient {

    public static void main(String[] args) {
        PlainOioClient plainOioClient = new PlainOioClient();
        try {
            plainOioClient.send(12344, "127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(int port, String address) throws IOException {
        Socket socket = new Socket(InetAddress.getByName(address), port);

        try {
            InputStream in = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String result = bufferedReader.readLine();
            System.out.println(result);
            OutputStream stream = socket.getOutputStream();

            stream.write("abc".getBytes(Charset.forName("UTF-8")));
            stream.flush();

            System.out.println(in.read());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

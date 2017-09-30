package com.ek.study.io;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/20
 */
public class ClientSocketStudy {

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 80);
        client.send();

        /*SecurityManager securityManager = new SecurityManager();
        //securityManager.checkConnect("baidu.com", -1);

        InetAddress inetAddress = InetAddress.getByName("baidu.com");
        System.out.println(inetAddress.getHostName());

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(networkInterface);
        }
        System.out.println(ProxySelector.getDefault());*/

    }

}

class Client {
    private String serverHost;
    private int serverPort;

    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void send() throws IOException {
        Socket socket = new Socket(InetAddress.getByName(this.serverHost), this.serverPort);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("abcdefg".getBytes(Charset.forName("utf-8")));
        outputStream.flush();


        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        int i = inputStream.read(bytes);
        System.out.println(i);
        System.out.println(new String(bytes));
        if (i == -1) {
            System.out.println("server close");
            return;
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        System.out.println(input);
        if (socket.isClosed() || socket.isOutputShutdown() || socket.isInputShutdown()) {
            System.out.println("server close...");
            return;
        }
        outputStream.write(input.getBytes(Charset.forName("utf-8")));
        outputStream.flush();
        int read = inputStream.read(bytes);
        System.out.println(new String(bytes));

        socket.close();
    }

}

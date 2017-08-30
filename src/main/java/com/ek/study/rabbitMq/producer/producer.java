package com.ek.study.rabbitMq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by maojinlin on 2017/5/25.
 */
public class producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        String message = "Hello World!.....";
        channel.basicPublish("", "hello", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}

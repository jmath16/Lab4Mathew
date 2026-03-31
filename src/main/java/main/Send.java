/** Project: Lab4 Pizza
 * Purpose Details: This program sends and receives Pizza objects using RabbitMQ and Web Services
 * Course: IST 242
 * Author: Jason Mathew
 * Date Developed: 3/28/26
 * Last Date Changed: 3/31/26
 * Rev: 1.0

 */

package main;

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Pizza pizza = new Pizza("Large", "Thin", "Pepperoni", 12.99);
        Gson gson = new Gson();
        String message = gson.toJson(pizza);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent JSON: " + message);
        }
    }
}

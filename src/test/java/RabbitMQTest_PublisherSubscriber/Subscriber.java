package RabbitMQTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Subscriber {

//    A subscriber is a component or application that consumes and processes messages from a message broker.
//    The subscriber expresses interest in receiving messages from a particular exchange by creating a queue
//    and binding it to that exchange.
//    When a message is published to the exchange, it is then routed to all queues that are bound to that exchange
//    based on the exchange type and routing rules.
//    The subscriber consumes messages from its associated queue.
    private static String EXCHANGE = "MyExchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        // Set up RabbitMQ connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Create a connection and channel
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declare a fanout exchange
        channel.exchangeDeclare(EXCHANGE, "fanout");

        // Declare a queue with a generated name
        String queueName = channel.queueDeclare().getQueue();

        // Bind the queue to the exchange with an empty routing key
        channel.queueBind(queueName, EXCHANGE, "");

        // Display a message indicating the subscriber is waiting for messages
        System.out.println("Waiting for messages. To exit press CTRL+C");

        // Callback for handling incoming messages
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received '" + message + "'");
        };

        // Consume messages from the queue and register the deliver callback
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}

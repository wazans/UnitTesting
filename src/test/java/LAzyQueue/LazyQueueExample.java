package LazyQueue;

import com.rabbitmq.client.*;
import java.util.HashMap;
import java.util.Map;

public class LazyQueueExample {

    private static final String QUEUE_NAME = "lazy_queue_demo";

    public static void main(String[] args) throws Exception {
        // Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Set RabbitMQ server host

        // Create a connection
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            // Declare a lazy queue with arguments
            Map<String, Object> queueArgs = new HashMap<>();
            queueArgs.put("x-queue-mode", "lazy");

            channel.queueDeclare(QUEUE_NAME, true, false, false, queueArgs);

            // Publish a message to the lazy queue
            String message = "Hello, Lazy Queue!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            // Consume messages from the lazy queue
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String receivedMessage = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + receivedMessage + "'");
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            // Wait for a moment to allow the consumer to process the message
            Thread.sleep(1000);
        }
    }
}

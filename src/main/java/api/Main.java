package api;

import io.nats.client.*;
import service.MessageProcessor;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting app...");
        System.out.println("Connecting to NATS...");

        Connection nc = Nats.connect("nats://localhost:4222");
        MessageProcessor processor = new MessageProcessor();

        Dispatcher d = nc.createDispatcher(msg -> {
            String received = new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println("Received message: " + received);

            processor.process(received);
        });

        d.subscribe("updates");

        System.out.println("Subscribed to 'updates'. Waiting for messages...");
    }
}

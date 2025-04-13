package service;

import data.MessageRepository;

/**
 * MessageProcessor is part of the service layer.
 * It validates incoming messages and delegates saving to the data layer.
 */
public class MessageProcessor {
    private MessageRepository repository;
    /**
     * Initializes the MessageProcessor by creating a MessageRepository instance.
     * This repository is used to persist valid messages.
     */
    public MessageProcessor() throws Exception {
        this.repository = new MessageRepository();
    }

    /**
     * Processes an incoming message:
     * - Validates the input
     * - Trims unnecessary whitespace
     * - Logs the operation
     * - Persists it to the database
     *
     * @param message The message to process
     */
    public void process(String message) {
        // 1. Basic validation: Ignore null or empty messages
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Empty message received. Skipping.");
            return;
        }

        // 2. Remove leading/trailing whitespace from the message
        message = message.trim();

        // 3. Print out the message being processed (for debugging/logging)
        System.out.println("Processing and saving message: " + message);

        // 4. Save the message to the database via the data layer
        repository.save(message);
    }
}

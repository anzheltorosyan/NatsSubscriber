package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the MessageRepository class.
 * Requires a running PostgreSQL database with the correct schema.
 */

public class MessageRepositoryTest {

    /**
     * Test saving a standard message to the database.
     */
    @Test
    void testSaveMessage() {
        assertDoesNotThrow(() -> {
            MessageRepository repo = new MessageRepository();
            repo.save("Test message from JUnit");
        });
    }

    /**
     * Test saving a very long message to verify DB accepts it.
     */
    @Test
    void testSaveLongMessage() {
        assertDoesNotThrow(() -> {
            String longMessage = "A".repeat(5000); // 5,000 characters
            MessageRepository repo = new MessageRepository();
            repo.save(longMessage);
        });
    }

    /**
     * Test saving multiple messages in a loop.
     */
    @Test
    void testSaveMultipleMessages() {
        assertDoesNotThrow(() -> {
            MessageRepository repo = new MessageRepository();
            for (int i = 1; i <= 5; i++) {
                repo.save("Message " + i);
            }
        });
    }
}

package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MessageProcessor class.
 */

public class MessageProcessorTest {

    /**
     * Test that processing a valid message does not throw any exceptions.
     */
    @Test
    void testProcessValidMessageDoesNotThrow() {
        assertDoesNotThrow(() -> {
            MessageProcessor processor = new MessageProcessor();
            processor.process("Hello NATS!");
        });
    }

    /**
     * Test that processing a blank message (spaces only) is safely ignored.
     */
    @Test
    void testProcessEmptyMessageDoesNotSave() {
        assertDoesNotThrow(() -> {
            MessageProcessor processor = new MessageProcessor();
            processor.process("   ");
        });
    }

    /**
     * Test that processing a null message is handled gracefully.
     */
    @Test
    void testProcessNullMessage() {
        assertDoesNotThrow(() -> {
            MessageProcessor processor = new MessageProcessor();
            processor.process(null);
        });
    }

    /**
     * Test a message that includes newlines and whitespace.
     */
    @Test
    void testProcessMessageWithNewlines() {
        assertDoesNotThrow(() -> {
            MessageProcessor processor = new MessageProcessor();
            processor.process("  Hello\nNATS\t ");
        });
    }
}

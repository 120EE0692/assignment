package visa.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ConsumerTest {

    @Test
    void testConsumerConsumesItemsAndStopsAtMinusOne() throws InterruptedException {
        // Destination list to store consumed items
        List<Integer> destination = new ArrayList<>();

        // Custom mock SharedQueue
        SharedQueue mockQueue = new SharedQueue(10) {
            private int index = 0;
            private final int[] data = {10, 20, 30, -1}; // -1 indicates stop

            @Override
            public synchronized int take() {
                return data[index++];
            }
        };

        // Create consumer
        Consumer consumer = new Consumer(mockQueue, destination);
        Thread thread = new Thread(consumer);

        // Run consumer
        thread.start();
        thread.join();

        // Assertions
        assertEquals(3, destination.size());
        assertEquals(List.of(10, 20, 30), destination);
    }

    @Test
    void testConsumerHandlesInterruption() {
        List<Integer> destination = new ArrayList<>();

        SharedQueue mockQueue = new SharedQueue(10) {
            @Override
            public synchronized int take() throws InterruptedException {
                throw new InterruptedException("Interrupted for testing");
            }
        };

        Consumer consumer = new Consumer(mockQueue, destination);
        Thread thread = new Thread(consumer);

        thread.start();

        // Interrupt immediately
        thread.interrupt();

        assertDoesNotThrow(() -> thread.join());

        // Consumer should not add anything
        assertTrue(destination.isEmpty());
    }
}

package visa.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProducerTest {

    @Test
    void testProducerProducesAllItemsAndSendsEndSignal() throws InterruptedException {
        List<Integer> source = List.of(1, 2, 3);
        List<Integer> captured = new ArrayList<>();

        // Mock SharedQueue to capture values
        SharedQueue mockQueue = new SharedQueue(10) {
            @Override
            public synchronized void put(int value) {
                captured.add(value);
            }
        };

        Producer producer = new Producer(mockQueue, source);
        Thread thread = new Thread(producer);

        thread.start();
        thread.join();

        // Verify items + end signal
        assertEquals(List.of(1, 2, 3, -1), captured);
    }

    @Test
    void testProducerHandlesInterruption() {
        List<Integer> source = List.of(5, 6);

        SharedQueue mockQueue = new SharedQueue(10) {
            @Override
            public synchronized void put(int value) throws InterruptedException {
                throw new InterruptedException("Forced interrupt for testing");
            }
        };

        Producer producer = new Producer(mockQueue, source);
        Thread thread = new Thread(producer);

        thread.start();
        thread.interrupt();

        assertDoesNotThrow(() -> thread.join());
    }
}

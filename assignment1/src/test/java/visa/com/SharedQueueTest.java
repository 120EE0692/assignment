package visa.com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SharedQueueTest {

    @Test
    void testPutAndTakeNormalFlow() throws InterruptedException {
        SharedQueue queue = new SharedQueue(3);

        queue.put(10);
        queue.put(20);

        assertEquals(10, queue.take());
        assertEquals(20, queue.take());
    }

    @Test
    void testTakeBlocksUntilItemAvailable() throws Exception {
        SharedQueue queue = new SharedQueue(2);

        Thread consumer = new Thread(() -> {
            try {
                // take() should block until producer adds item
                assertEquals(99, queue.take());
            } catch (InterruptedException e) {
                fail("Should not interrupt");
            }
        });

        consumer.start();

        // Give consumer time to block on empty queue
        Thread.sleep(50);

        queue.put(99);

        consumer.join();
    }

    @Test
    void testPutBlocksWhenFull() throws Exception {
        SharedQueue queue = new SharedQueue(1);

        queue.put(1); // fills queue

        Thread producer = new Thread(() -> {
            try {
                queue.put(2); // must wait until consumer removes item
            } catch (InterruptedException e) {
                fail("Should not interrupt");
            }
        });

        producer.start();

        // Allow producer to block
        Thread.sleep(50);

        assertTrue(producer.isAlive(), "Producer should be blocked on full queue");

        // Now take one item so producer can continue
        assertEquals(1, queue.take());

        producer.join();
    }

    @Test
    void testFIFOOrder() throws Exception {
        SharedQueue queue = new SharedQueue(5);

        queue.put(5);
        queue.put(6);
        queue.put(7);

        assertEquals(5, queue.take());
        assertEquals(6, queue.take());
        assertEquals(7, queue.take());
    }

}

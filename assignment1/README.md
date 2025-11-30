# Assignment 1 — Producer / Consumer Example

Overview
- This assignment demonstrates a simple producer/consumer pattern using a shared queue and multiple threads.

File structure
- `src/main/java/visa/com/Producer.java` — Produces items and enqueues them to the shared queue. Typically runs in its own thread.
- `src/main/java/visa/com/Consumer.java` — Consumes items from the shared queue and processes them. Typically runs in its own thread.
- `src/main/java/visa/com/SharedQueue.java` — Thread-safe queue used to pass items between producers and consumers. Implements synchronization/coordination (wait/notify or concurrency utilities).
- `src/main/java/visa/com/Main.java` — Entrypoint that wires producers and consumers together, starts threads, and demonstrates program behavior.

Tests
- `src/test/java/visa/com/ProducerTest.java` — Unit tests for producer behavior.
- `src/test/java/visa/com/ConsumerTest.java` — Unit tests for consumer behavior.
- `src/test/java/visa/com/SharedQueueTest.java` — Tests queue synchronization and basic enqueue/dequeue semantics.
- `src/test/java/visa/com/MainTest.java` — Integration-style test for `Main` (if present).

Logic summary
- Producer: creates or generates items (simple objects or numbers) and attempts to add them to the `SharedQueue`. If the queue is full (depending on implementation) the producer will block or wait until space is available.
- Consumer: waits for items to become available on the `SharedQueue`. When an item is available the consumer removes it and performs processing (for tests this usually means recording or asserting correctness).
- SharedQueue: central coordination point — implemented to be thread-safe (synchronized methods or Java concurrency classes such as `BlockingQueue`). It should correctly handle multi-threaded access and support basic operations such as `enqueue`, `dequeue`, and a method to check emptiness/size.

How to run
1. Make sure Java (JDK) and Maven are installed and on `PATH`.
2. From the assignment directory run tests:

```powershell
cd assignment1; mvn test
```


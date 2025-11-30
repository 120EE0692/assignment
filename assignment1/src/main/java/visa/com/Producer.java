package visa.com;
import java.util.List;


public class Producer implements Runnable {
    private final SharedQueue queue;
    private final List<Integer> source;

    public Producer(SharedQueue queue, List<Integer> source) {
        this.queue = queue;
        this.source = source;
    }

    @Override
    public void run() {
        try {
            for (int item : source) {
                queue.put(item);
                Thread.sleep(20); // Simulate time taken to produce an item
            }
            queue.put(-1); // Indicate end of production
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }   
}

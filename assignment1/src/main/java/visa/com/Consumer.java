package visa.com;

import java.util.List;

public class Consumer implements Runnable {
    private final SharedQueue queue;
    private final List<Integer> destination;


    public Consumer(SharedQueue queue, List<Integer> destination) {
        this.queue = queue;
        this.destination = destination;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = queue.take();
                if(item == -1) break;
                destination.add(item);
                Thread.sleep(3000);
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
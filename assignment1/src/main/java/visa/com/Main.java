package visa.com;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        List<Integer> source = Arrays.asList(1,2,3,4,5);
        List<Integer> destination = new ArrayList<>();

        SharedQueue queue = new SharedQueue(3);

        Thread producer = new Thread(new Producer(queue, source));
        Thread consumer = new Thread(new Consumer(queue, destination));

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Final destination container: " + destination);
    }
}
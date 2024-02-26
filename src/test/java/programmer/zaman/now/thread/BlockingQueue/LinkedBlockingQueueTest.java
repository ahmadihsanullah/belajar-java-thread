package programmer.zaman.now.thread.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class LinkedBlockingQueueTest {
    @Test
    void testLinkedBlockingQueue() throws InterruptedException{
        /**
         * An optionally-bounded blocking deque based on linked nodes. 
        */

        final var queue = new LinkedBlockingQueue<String>(); // queue dapat berkembang
        final var executor = Executors.newFixedThreadPool(20);
        
        for (int i = 0; i < 10; i++) {
            // 10 thread mengeksekusi
            executor.execute(()->{
                try {
                    queue.put("Data"); // 
                    System.out.println("Finish Put Data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(()->{
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take(); // mengambil 1 per 1 datanya
                    System.out.println("Receive data : " + value );
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

package programmer.zaman.now.thread.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class arrayBlockingQueueTest {
    @Test
    void testArrayBlockingQueue() throws InterruptedException{
        /**
         * A Queue that additionally supports operations
        that wait for the queue to become non-empty when retrieving an element, 
        and wait for space to become available in the queue when storing an element.
        */

        final var queue = new ArrayBlockingQueue<>(5);
        final var executor = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 10; i++) {
            // 10 thread mengeksekusi
            executor.execute(()->{
                try {
                    queue.put("Data"); // queue hanya dapat menampung 5 data
                    System.out.println("Finish Put Data");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }

        executor.execute(()->{
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take(); // mengambil 1 per 1 datanya, sehingga queue berkurang dan dapat diisi lagi
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

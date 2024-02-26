package programmer.zaman.now.thread.BlockingQueue;

import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class PriorityBlockingQueueTest {
    @Test
    void testPriorityBlockingQueue() throws InterruptedException{

        final var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        final var executor = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 10; i++) {
            var index = i;
            executor.execute(()->{
                queue.put(index);
                System.out.println("Success put data : " + index);
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

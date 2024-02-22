package programmer.zaman.now.thread.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CyclicBarrierTest {
    
    @Test
    void testCyclicBarrier() throws InterruptedException{
        final var cyclicBarrier = new CyclicBarrier(5); // menunggu sampai 5 thread
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(()->{
                try {
                    System.out.println("Waiting"); 
                    cyclicBarrier.await(); // thread menunggu disini sampai 5, jika sudah baru bisa dieksekusi kebawahnya
                    System.out.println("DONE");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}

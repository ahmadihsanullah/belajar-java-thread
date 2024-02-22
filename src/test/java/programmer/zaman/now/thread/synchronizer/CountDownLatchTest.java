package programmer.zaman.now.thread.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.util.unit.DataUnit;

public class CountDownLatchTest {

    @Test
    void testCountDownLatch() throws InterruptedException{
        final var countDown = new CountDownLatch(5); //5 counter
        final var executor = Executors.newFixedThreadPool(10); //10 thread

        for(int i = 0 ; i < 5 ; i++){
            executor.execute(()->{
                try {
                    System.out.println("Start Task");
                    Thread.sleep(2000);
                    System.out.println("Finish Task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    countDown.countDown(); //menurunkan counter
                }
            });
        }

        executor.execute(()->{
            try {
                countDown.await();//menunggu counter sampai 0
                System.out.println("Finish Task All");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}

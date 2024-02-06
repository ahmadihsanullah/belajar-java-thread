package programmer.zaman.now.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ExecutorServiceTest {

    @Test
    void testExecutorService() throws InterruptedException {
        
        var executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable run in Thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    void testExecutorServiceFix() throws InterruptedException {
        
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable run in Thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
    
}
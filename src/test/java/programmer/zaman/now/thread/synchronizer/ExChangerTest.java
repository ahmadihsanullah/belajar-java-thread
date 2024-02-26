package programmer.zaman.now.thread.synchronizer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ExChangerTest {
    @Test
    void test() throws InterruptedException{
        final var exChanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);
        
        executor.execute(()->{
            try {
                System.out.println("Thread 1 : send : first");
                Thread.sleep(1000);
                var result = exChanger.exchange("first");
                System.out.println("Thread 1 : received : " + result);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        executor.execute(()->{
            try {
                System.out.println("Thread 2 : send : second");
                Thread.sleep(2000);
                var result = exChanger.exchange("second");
                System.out.println("Thread 2 : received : " + result);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

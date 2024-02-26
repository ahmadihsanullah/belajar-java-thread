package programmer.zaman.now.thread.BlockingQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class synchronousQueueTest {
    //kalo belum ada yang ngambil, data belum bisa masuk
    @Test
    void synchronousQueue() throws InterruptedException {
        final var queue = new SynchronousQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.put("Data-" + index);
                    System.out.println("Finish Put Data : " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("Receive data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

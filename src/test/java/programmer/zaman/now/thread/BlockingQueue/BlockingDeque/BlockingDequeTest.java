package programmer.zaman.now.thread.BlockingQueue.BlockingDeque;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class BlockingDequeTest {
    // bisa melakukan LIFO dan FIFO
     @Test
    void blockingDeque() throws InterruptedException {
        final var queue = new LinkedBlockingDeque<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
        final var index = i;
        try {
            queue.putLast("Data-" + index);
            System.out.println("Finish Put Data : " + index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

        executor.execute(() -> {
        while (true) {
            try {
            Thread.sleep(2000);
            var value = queue.takeFirst();
            System.out.println("Receive data : " + value);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}

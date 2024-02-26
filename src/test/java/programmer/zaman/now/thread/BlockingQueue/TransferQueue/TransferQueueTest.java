package programmer.zaman.now.thread.BlockingQueue.TransferQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class TransferQueueTest {
    //seperti synchronous di blocking queue
    @Test
    void transferQueue() throws InterruptedException {
        final var queue = new LinkedTransferQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
        final var index = i;
        executor.execute(() -> {
            try {
            queue.transfer("Data-" + index);
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

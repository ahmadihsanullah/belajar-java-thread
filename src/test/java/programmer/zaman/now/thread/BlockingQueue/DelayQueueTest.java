package programmer.zaman.now.thread.BlockingQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class DelayQueueTest {
    @Test
    void delayedQueue() throws InterruptedException {
        final var queue = new DelayQueue<ScheduledFuture<String>>();
        final var executor = Executors.newFixedThreadPool(20);
        final var executorScheduled = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final var index = i;
            queue.put(executorScheduled.schedule(() -> "Data " + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
        while (true) {
            try {
            var value = queue.take();
            System.out.println("Receive data : " + value.get());
            } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            }
        }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}

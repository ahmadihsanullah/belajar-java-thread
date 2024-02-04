package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import programmer.zaman.now.thread.race_condition.Counter;
import programmer.zaman.now.thread.race_condition.SynchronizedCounter;

public class RaceConditionTest {
    
    @Test
    void counter() throws InterruptedException{

        var counter = new Counter();
        Runnable runnable = () -> {
           for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
           }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());

    }

    @Test
    void counterWithSynchronization() throws InterruptedException{

        var counter = new SynchronizedCounter();
        Runnable runnable = () -> {
           for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
           }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());

    }
}

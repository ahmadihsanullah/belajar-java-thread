package programmer.zaman.now.thread.synchronizer;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class PhaserTest {
    
    @Test
    void testPhaserAsCountDownLatch() throws InterruptedException{
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5); // 5 counter
        for (int i = 0; i < 5; i++) {
            executor.execute(()->{
                try {
                    System.out.println("Start task");
					Thread.sleep(2000);
                    System.out.println("Finish task");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
                    phaser.arrive();//menurunkan counter
                }
            });
        }

        executor.execute(()->{
            phaser.awaitAdvance(0); //menunggu sampai counter 0
            System.out.println("All task done");
        });
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testPhaserAsCyclicBarrier() throws InterruptedException{
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5); //menaruh jumlah thread yang harus terpenuhi untuk saling menunggu

        for (int i = 0; i < 5; i++) {
            executor.execute(()->{
                phaser.arriveAndAwaitAdvance(); //menunggu sampe phaser 5
                System.out.println("DONE");
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

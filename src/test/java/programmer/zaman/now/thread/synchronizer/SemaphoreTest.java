package programmer.zaman.now.thread.synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class SemaphoreTest {
    @Test
    // membatasi pekerjaan yang dijalankan
    // threadnya mah tetap 10 disini
    void testSemaphore() throws InterruptedException{
        final Semaphore semaphore = new Semaphore(1); //yang diperbolehkan cuman 1
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    // 10 thread nunggu disini
                    semaphore.acquire(); //boleh dinaikin 1 saja, jika sudah 1 yg lain nunggu tunggu release
                    Thread.sleep(1000);
                    System.out.println("Finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    semaphore.release(); // nuruinin semapore, sehingga thread bisa dimasuk lagi 
                }
            });
        }

        executor.awaitTermination(1,TimeUnit.DAYS);
    }
}

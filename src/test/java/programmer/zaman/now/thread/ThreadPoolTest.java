package programmer.zaman.now.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ThreadPoolTest {
    
    @Test
    void create(){

        var mainThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var threadPool = new ThreadPoolExecutor(mainThread, maxThread, alive, aliveTime, queue);
    }

    @Test
    void executeRunnable() throws InterruptedException{

        var mainThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(mainThread, maxThread, alive, aliveTime, queue);
        Runnable runnable = ()->{
            try {
                Thread.sleep(5000);
                System.out.println("Runnable from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        };
        executor.execute(runnable);
        Thread.sleep(6000);
    }


    @Test
    void shutdown() throws InterruptedException{
        /**
         * saat ini mengatur mainThread sebanyak 10, 
        yang artinya executor akan menggunakan hingga 10 thread 
        untuk mengeksekusi tugas secara bersamaan. Oleh karena itu, 
        meskipun memiliki 1000 tugas di dalam antrian (queue), 
        hanya 10 dari mereka yang akan dieksekusi secara bersamaan.
        **/

        var mainThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(mainThread, maxThread, alive, aliveTime, queue);
        for (int i = 0; i < 50; i++) {
            final var task = i;
            Runnable runnable = ()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable from thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            };
            executor.execute(runnable);
        }
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task " + r + " is rejected");
        }
    }

    @Test
    void rejected() throws InterruptedException {
    // queue penuh dan thread juga semua sedang bekerja

      var minThread = 10;
      var maxThread = 100; //<- max thread melebihi kapasitas queue
      var alive = 1;
      var aliveTime = TimeUnit.MINUTES;
      var queue = new ArrayBlockingQueue<Runnable>(10);
      var rejectedHandler = new LogRejectedExecutionHandler();
  
      var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue, rejectedHandler); //<- nambah 1 parameter
  
      for (int i = 0; i < 1000; i++) {
        final var task = i;
        Runnable runnable = () -> {
          try {
            Thread.sleep(1000);
            System.out.println("Task " + task + " from thread : " + Thread.currentThread().getName());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        };
        executor.execute(runnable);
      }
  
      // executor.shutdownNow();
      executor.awaitTermination(1, TimeUnit.DAYS);
    }


}

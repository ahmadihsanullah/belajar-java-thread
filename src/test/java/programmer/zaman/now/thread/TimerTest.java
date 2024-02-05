package programmer.zaman.now.thread;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

public class TimerTest {
    //sama seperti runnable, untuk membuat dan mengeksekusi thread
     @Test
    void delayedJob() throws InterruptedException {
        // TimerJob -> berjalan 1 kali
        var task = new TimerTask(){
        @Override
        public void run() {
            System.out.println("Delayed Job");
        }
        };

        var timer = new Timer();
        timer.schedule(task, 2000);

        Thread.sleep(3000L);

    }

    @Test
    void periodicJob() throws InterruptedException {
        // TimerJob -> berjalan berkali-kali
        var task = new TimerTask(){
        @Override
        public void run() {
            System.out.println("Delayed Job");
        }
        };

        var timer = new Timer();
        timer.schedule(task, 2000, 2000);

        Thread.sleep(10_000L);

    }
}

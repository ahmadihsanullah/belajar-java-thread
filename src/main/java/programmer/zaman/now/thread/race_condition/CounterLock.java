package programmer.zaman.now.thread.race_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    
    private Long value = 0L;

    final private Lock lock = new ReentrantLock();

    public void increment(){
        try{
            lock.lock();
            value++;
        }finally{
            lock.unlock(); // wajib finally, agar diunlock lagi datanya. menghindari exception
        }
    }

    public Long getValue() {
        return value;
    }
}

package programmer.zaman.now.thread.race_condition;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {
    
    private Long value = 0L;

    final private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment(){
        try{
            lock.writeLock().lock();
            value++;
        }finally{
            lock.writeLock().unlock(); // wajib finally, agar diunlock lagi datanya. menghindari exception
        }
    }

    public Long getValue() {
        try{
            lock.readLock().lock();
            return value;
        }finally{
            lock.readLock().unlock();
        }
    }
}

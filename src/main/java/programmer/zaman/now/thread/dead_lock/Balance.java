package programmer.zaman.now.thread.dead_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Balance {
    
    private Long value;

    final static private Lock lock = new ReentrantLock();

    public Balance(Long value){
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static void transferDeadLock(Balance from, Balance to, Long value) throws InterruptedException{
        synchronized(from){
            Thread.sleep(1_000);
            synchronized(to){
                Thread.sleep(1_000);
                from.setValue(from.getValue() - value);
                to.setValue(to.getValue() + value);
            }
        }
    }

    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException{
        synchronized(from){
            Thread.sleep(1_000);
            from.setValue(from.getValue() - value);
        }
        synchronized(to){
            Thread.sleep(1_000);
            to.setValue(to.getValue() + value);
        }
    }

    public static void transferWithLock(Balance from, Balance to, Long value) throws InterruptedException{
        try{
            lock.lock();
            from.setValue(from.getValue() - value);
            Thread.sleep(1_000);
            to.setValue(to.getValue() + value);
        }finally{
            lock.unlock();
        }
    }

}

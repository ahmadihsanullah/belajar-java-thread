package programmer.zaman.now.thread.race_condition;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {
    
    private AtomicLong value = new AtomicLong(0L);

    public void increment(){
        value.incrementAndGet();
    }

    public AtomicLong getValue() {
        return value;
    }
}

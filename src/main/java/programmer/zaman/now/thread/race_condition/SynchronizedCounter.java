package programmer.zaman.now.thread.race_condition;

public class SynchronizedCounter {
    
    private Long value = 0L;

    public void increment() {
        // kode lain  <- ini bisa dieksekusi oleh banyak thread

        // Synchronized Statement
        synchronized (this) { // hanya 1 thread yang mengeksekusi, yang lain menunggu
        value++;
        }

        //kode lain <- ini bisa dieksekusi oleh banyak thread
    }

    public Long getValue() {
        return value;
    }
    
}

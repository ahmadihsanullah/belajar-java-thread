package programmer.zaman.now.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class FutureTest {
    
    @Test
    void testFuture() throws InterruptedException, ExecutionException{

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Future selesai");

        while(!future.isDone()){ //mencek status
            System.out.println("Menunggu selesai");
            Thread.sleep(1000);
        }

        String value = future.get(); //mendapat kan hasil 
        System.out.println(value);
    }

    @Test
    void testFutureCancel() throws InterruptedException, ExecutionException{

        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Future selesai");

        Thread.sleep(2000);
        future.cancel(true); // diinterrup -> mengirim sinyal ke thread

        String value = future.get(); 
        System.out.println(value);
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        
        /**invokeAll(Collection<Callable<T>>) 
        untuk mengeksekusi banyak Callable secara sekaligus
        **/

        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
        Thread.sleep(value * 500L);
        return String.valueOf(value);
        }).collect(Collectors.toList());

        var futures = executor.invokeAll(callables);

        for (Future<String> stringFuture : futures) {
        System.out.println(stringFuture.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        
        /**invokeAny(Collection<Callable<T>>) 
        mengembalikan result data dari Callable yang paling cepat mengembalikan result
        **/

        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
        Thread.sleep(value * 500L);
        return String.valueOf(value);
        }).collect(Collectors.toList());

        var value = executor.invokeAny(callables);
        System.out.println(value);

    }
}

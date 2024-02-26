package programmer.zaman;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ConcurrentMapTest {
     @Test
    void concurrentMap() throws InterruptedException {

        final var countDown = new CountDownLatch(100);
        final var map = new ConcurrentHashMap<Integer, String>();
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
        final var index = i;
        executor.execute(() -> {
            try {
            Thread.sleep(1000);
            map.putIfAbsent(index, "Data-" + index); // masukan ke map
            } catch (InterruptedException e) {
            e.printStackTrace();
            } finally {
            countDown.countDown(); // count down latch diturunin sampe 100
            }
        });
        }

        executor.execute(() -> {
        try {
            countDown.await(); // nunggu sampe 0, baru di eksekusi yang dibawahnya
            map.forEach((integer, s) -> System.out.println(integer + " : " + s));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    

    @Test
    void testCollection() throws InterruptedException {
        final var executor = Executors.newFixedThreadPool(10);
        List<String> list = List.of("Eko", "Kurniawan", "Khannedy");
        List<String> synchronizedList = Collections.synchronizedList(list);
        
        // for (int i = 0; i < list.size(); i++) {
        //     var index = i;
        //     executor.execute(()->{
        //         try {
		// 			Thread.sleep(1000);
        //             System.out.println(list.get(index) + "pertama");
		// 		} catch (InterruptedException e) {
		// 			// TODO Auto-generated catch block
		// 			e.printStackTrace();
		// 		}
        //     });
        // }

        // for (int i = 0; i < list.size(); i++) {
        //     var index = i;
        //     executor.execute(()->{
        //             try {
		// 				Thread.sleep(1000);
        //                 System.out.println(list.get(index) + "kedua");
		// 			} catch (InterruptedException e) {
		// 				// TODO Auto-generated catch block
		// 				e.printStackTrace();
		// 			}
        //     });
        // }


        synchronizedList.forEach(item -> {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(item + "pertama");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        
        synchronizedList.forEach(item -> {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(item + "kedua");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

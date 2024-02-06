package programmer.zaman.now.thread;

public class MainApplication {
    public static void main(String[] args) {
        // var name = Thread.currentThread().getName();
        // System.out.println(name);

        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };
      
          var thread = new Thread(runnable);
        //   thread.setDaemon(true); Thread Daemon membuat thread tidak menunggu program selesai
          thread.start();
      
          System.out.println("Program Selesai");
    }
}

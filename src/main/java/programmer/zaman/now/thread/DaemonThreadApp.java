package programmer.zaman.now.thread;

public class DaemonThreadApp {
    // Java (bukan JUnit) secara default akan selalu menunggu semua user thread selesai sebelum program berhenti
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            try {
              Thread.sleep(3000); // <-- ini user thread
              System.out.println("Run Thread");
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
      
        //   thread.setDaemon(false); // --> menunggu semua user thread selesai sebelum program berhenti
          thread.setDaemon(true); // --> tidak akan ditunggu jika memang program Java akan berhenti
          thread.start();
    }
}

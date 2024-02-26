package programmer.zaman.now.thread.service;

public class UserService {
    final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setUser(String user){
        threadLocal.set(user);
    }

    public void doAction(){
        String user = threadLocal.get();
        System.out.println(user + "do action");
    }
}

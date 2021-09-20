package first;

public class Pingo {
    public static void main(String[] args) {
        Object obj = new Object();
        Thread ping = new Thread(new myThread(obj, "PING "));
        Thread pong = new Thread(new myThread(obj, "PONG "));
        ping.start();
        pong.start();

    }
}

class myThread implements Runnable {
    String name;
    Object obj;
    Boolean flag = true;
    myThread(Object obj, String name){
        this.name = name;
        this.obj = obj;
    }


    public void run() {
        synchronized(obj) {
            while(flag){
                obj.notify();
                System.out.print(name);
                try {
                    Thread.sleep(200);
                    obj.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

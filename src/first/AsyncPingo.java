package first;

public class AsyncPingo {
    public static void main(String[] args) throws InterruptedException {

        myaThread ping = new myaThread( "PING ");
        myaThread pong = new myaThread( "PONG ");
        ping.start();
        pong.start();
        Thread.sleep(3000);
        System.out.println("...");
        ping.flag = false;


    }
}

class myaThread extends Thread {
    String name;

    public Boolean flag = true;
    myaThread( String name){
        this.name = name;

    }

    public void run() {
            while(flag) {
                System.out.print(name);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
    }
}

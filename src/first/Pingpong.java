package first;

public class Pingpong {
    public static void main(String[] args) {
        Table table = new Table();
        Ping ping = new Ping(table);
        Pong pong = new Pong(table);
        new Thread(ping).start();
        new Thread(pong).start();
    }
}
class Table {
    private int num = 1;

    public synchronized void ping() {
        while (num % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Cringe, bro...");
            }
        }
        System.out.print("PING ");
        num++;
        notify();
    }

    public synchronized void pong() {
        while (num % 2 == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Cringe, bro...");
            }
        }
        System.out.print("PONG ");
        num++;
        notify();
    }
}

class Ping implements Runnable {
    Table table;
    Ping(Table table) {
        this.table = table;
    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            table.ping();
        }
    }
}

class Pong implements Runnable {
    Table table;
    Pong(Table table) {
        this.table = table;
    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            table.pong();
        }
    }
}

package third;
import java.net.*;
import java. io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MailServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private ArrayList<String> history;
        private long lastSended;
        private Socket clientSocket;
        private PrintWriter out;
        private Scanner in;
        private int bookmark = 0;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.lastSended = System.currentTimeMillis();
        }

        public void run() {
            System.out.println("Подключение: " + clientSocket);
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                in = new Scanner(clientSocket.getInputStream());

                while (true) {
                    if (in.hasNextLine()) {
                        history.add(in.nextLine());
                    }

                }
            } catch (Exception e) {
                System.out.println("Ошибка:" + clientSocket);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + clientSocket);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ArrayList<String> allHistory;
        System.out.println("Сервер запущен...");
        MailServer server = new MailServer();
        server.start(5555);

    }
}

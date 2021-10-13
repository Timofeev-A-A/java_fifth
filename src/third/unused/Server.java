package third.unused;
import java.net.*;
import java. io.*;
import java.util.Scanner;

public class Server {
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
        private Socket clientSocket;
        private PrintWriter out;
        private Scanner in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            System.out.println("Подключение: " + clientSocket);
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                in = new Scanner(clientSocket.getInputStream());

                while (in.hasNextLine()) {

                    out.println(in.nextLine().toUpperCase());
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
        System.out.println("Сервер запущен...");
        Server server = new Server();
        server.start(5555);

    }
}

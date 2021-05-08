package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final int listeningIntervalMS;
    private final IServerStrategy strategy;
    private final ExecutorService threadPool; // Thread pool
    private volatile boolean run = false;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getInstance().getThreadPoolSize());
    }

    public void start() {
        Thread server = new Thread(() -> {
            this.run();
            System.out.println("Server stopped");
        });
        server.start();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.listeningIntervalMS);
            System.out.println("Starting server at port = " + this.port);
            this.run = true;

            while (this.run) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    // Insert the new task into the thread pool
                    this.threadPool.execute(() -> handleClient(clientSocket)); //TODO: Souled be execute or submit??
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout");
                }
            }
            serverSocket.close(); //TODO: needed??
        } catch (IOException e) {
            this.run = false;
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("Stopping server...");
        this.run = false;
        this.threadPool.shutdownNow();
    }

    private void handleClient(Socket clientSocket) {
        try {
            this.strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            this.run = false;
            e.printStackTrace();
        }
        System.out.println("Done handling client: " + clientSocket.toString());
    }


}

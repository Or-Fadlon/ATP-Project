package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ConnectException;

public class Client {
    private final InetAddress serverIP;
    private final int serverPort;
    private final IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer() {
        try {
            Socket serverSocket = new Socket(this.serverIP, this.serverPort);
            System.out.printf("Client connected to server: %s:%s)%n", this.serverIP, this.serverPort);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
            serverSocket.close();
        } catch (ConnectException e) {
            System.out.println("Cant find server...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

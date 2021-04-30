package test;

import Server.*;

public class mazeGeneratorServer {
    public static void main(String[] args) {
        Server mazeGeneratingServer = new Server(5400, 3000, new ServerStrategyGenerateMaze());
        Thread t1 = new Thread(() -> mazeGeneratingServer.start());
        t1.start();

    }
}

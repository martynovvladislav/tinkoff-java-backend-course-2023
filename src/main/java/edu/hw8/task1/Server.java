package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final static int PORT = 3345;
    private ServerSocket serverSocket;

    @SuppressWarnings("checkstyle:MagicNumber")
    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(3000);
            Thread.sleep(2000);
            while (true) {
                executorService.execute(new ClientHandler(serverSocket.accept()));
            }
        } catch (SocketTimeoutException e) {
            close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        executorService.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

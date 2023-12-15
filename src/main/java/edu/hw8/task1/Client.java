package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private static final int PORT = 3345;

    public void start() {
        try {
            this.socket = new Socket("localhost", PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public String sendMessage(String message) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeUTF(message);
            out.flush();
            Thread.sleep(10);
            return in.readUTF();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

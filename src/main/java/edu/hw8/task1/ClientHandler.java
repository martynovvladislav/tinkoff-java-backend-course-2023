package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    private final static Map<String, String> ANSWERS = new HashMap<>(Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    ));

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            while (!clientSocket.isClosed()) {
                String clientMessage;
                try {
                    clientMessage = in.readUTF();
                } catch (IOException e) {
                    break;
                }
                out.writeUTF(handleMessage(clientMessage));
                out.flush();
                Thread.sleep(10);
            }

            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String handleMessage(String clientMessage) {
        return ANSWERS.getOrDefault(clientMessage, "Сам такой!");
    }
}

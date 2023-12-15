package edu.hw6.task6.portUtils;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortHandler {
    private static final int PORTS_AMOUNT = 49151;
    private static final String FORMATTER = "%-10s%-10s%-40s%n";
    private static final Logger LOGGER = LogManager.getLogger();
    public List<Port> ports = new ArrayList<>();

    public List<Port> scan() {
        for (int i = 0; i <= PORTS_AMOUNT; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
            } catch (IOException e) {
                ports.add(new Port(i, Port.Protocol.TCP, ServiceList.SERVICES.get(i)));
            }

            try {
                DatagramSocket datagramSocket = new DatagramSocket(i);
            } catch (SocketException e) {
                ports.add(new Port(i, Port.Protocol.UDP, ServiceList.SERVICES.get(i)));
            }
        }
        return ports;
    }

    public void printPorts() {
        LOGGER.info(String.format(FORMATTER, "Протокол", "Порт", "Сервис"));
        LOGGER.info("----------------------------------------------------");
        ports.forEach(port -> LOGGER.info(String.format(
                FORMATTER,
                port.getProtocol(),
                port.getId(),
                port.getService()
            ))
        );
    }
}

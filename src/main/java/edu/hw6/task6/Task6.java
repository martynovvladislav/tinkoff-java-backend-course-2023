package edu.hw6.task6;

import edu.hw6.task6.portUtils.PortHandler;

public class Task6 {
    private Task6() {}

    public static void run() {
        PortHandler portHandler = new PortHandler();
        portHandler.scan();
        portHandler.printPorts();
    }
}

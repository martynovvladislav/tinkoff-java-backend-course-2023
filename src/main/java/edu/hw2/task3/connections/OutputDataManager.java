package edu.hw2.task3.connections;

public class OutputDataManager {
    private OutputDataManager() {}

    public static String getSuccessfulOutput(String command) {
        return "Executed " + command + " successfully!";
    }

    public static String getClosedOutput() {
        return "Connection closed";
    }
}

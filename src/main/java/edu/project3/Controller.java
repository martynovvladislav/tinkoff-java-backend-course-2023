package edu.project3;

import edu.project3.logHandlers.LogsCollector;
import edu.project3.logHandlers.StatisticsHandler;

public class Controller {
    private Controller() {}

    public static void run(String[] args) {
        UserInput userInput = new UserInput();
        userInput.parseArguments(args);
        userInput.handleArguments();
        LogsCollector collector = new LogsCollector();
        collector.collectLogs(userInput.getArguments());
        StatisticsHandler statisticsHandler = new StatisticsHandler(userInput.getArguments(), collector.getLogs());
        statisticsHandler.handleStatistics();
    }
}

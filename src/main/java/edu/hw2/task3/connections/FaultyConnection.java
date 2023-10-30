package edu.hw2.task3.connections;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RAND = new Random();

    @Override
    public void execute(String command) {
        if (RAND.nextBoolean()) {
            LOGGER.info("Connection's failure");
            throw new ConnectionException("Failed connection");
        } else {
            LOGGER.info(OutputDataManager.getSuccessfulOutput(command));
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info(OutputDataManager.getClosedOutput());
    }
}

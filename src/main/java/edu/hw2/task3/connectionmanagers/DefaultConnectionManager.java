package edu.hw2.task3.connectionmanagers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Random RAND = new Random();

    @Override
    public Connection getConnection() {
        if (RAND.nextBoolean()) {
            return new StableConnection();
        }
        return new FaultyConnection();
    }
}

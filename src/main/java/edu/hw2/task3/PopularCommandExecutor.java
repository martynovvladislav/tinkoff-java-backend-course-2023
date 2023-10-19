package edu.hw2.task3;

import edu.hw2.task3.connectionmanagers.ConnectionManager;
import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.ConnectionException;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        try (Connection connection = manager.getConnection()) {
            ConnectionException savedException = null;
            int attempts = 0;
            while (attempts < maxAttempts) {
                try {
                    connection.execute(command);
                    break;
                } catch (ConnectionException e) {
                    savedException = e;
                    attempts++;
                }
            }
            if (attempts == maxAttempts) {
                throw new ConnectionException(savedException);
            }
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Close error");
        }
    }
}

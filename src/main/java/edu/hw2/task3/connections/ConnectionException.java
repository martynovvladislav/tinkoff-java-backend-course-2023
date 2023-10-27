package edu.hw2.task3.connections;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String cause) {
        super(cause);
    }
}

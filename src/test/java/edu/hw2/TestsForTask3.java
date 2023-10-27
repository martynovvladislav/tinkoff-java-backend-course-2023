package edu.hw2;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.connectionmanagers.ConnectionManager;
import edu.hw2.task3.connectionmanagers.DefaultConnectionManager;
import edu.hw2.task3.connectionmanagers.FaultyConnectionManager;
import edu.hw2.task3.connections.ConnectionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestsForTask3 {
    static Stream<Arguments> test1Args() {
        return Stream.of(
            Arguments.of(new DefaultConnectionManager(), 10),
            Arguments.of(new FaultyConnectionManager(), 20)
        );
    }

    @ParameterizedTest
    @DisplayName("Executing commands test")
    @MethodSource("test1Args")
    void executingTest(ConnectionManager manager, int maxAttempts) {
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        executor.updatePackages();
    }

    @Test
    @DisplayName("Exceptions throwing test")
    void exceptionThrowingTest() {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        Assertions.catchException(() -> {
            for (int i = 0; i < 100; i++) {
                executor.updatePackages();
            }
        }).equals(new ConnectionException());
    }
}

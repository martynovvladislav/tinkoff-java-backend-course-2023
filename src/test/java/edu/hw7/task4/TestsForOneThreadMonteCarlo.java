package edu.hw7.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class TestsForOneThreadMonteCarlo {
    static Stream<Arguments> iterationsAmount() {
        return Stream.of(
            Arguments.of(1000000),
            Arguments.of(10000000),
            Arguments.of(100000000)
        );
    }

    @ParameterizedTest
    @DisplayName("different iterations test")
    @MethodSource("iterationsAmount")
    void OneThreadMonteCarloTest(int iterationsAmount) {
        OneThreadMonteCarlo oneThreadMonteCarlo = new OneThreadMonteCarlo();
        oneThreadMonteCarlo.calculatePiReport(iterationsAmount);
    }
}

package edu.project3;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForUserInput {

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
            Arguments.of(
                new String[] {"--path", "somePath", "--from", "2023/10/23", "--to", "2023/10/23", "--format",
                    "someFormat"},
                new HashMap<String, String>(Map.of(
                    "path", "somePath", "from", "2023/10/23", "to", "2023/10/23", "format", "someFormat"
                ))
            ),
            Arguments.of(
                new String[] {"--path", "somePath", "--format", "someFormat"},
                new HashMap<String, String>(Map.of("path", "somePath", "format", "someFormat"
                ))
            )
        );
    }

    @ParameterizedTest
    @DisplayName("cmd arguments parse test")
    @MethodSource("argumentsStream")
    void parseArgumentsTest(String[] args, Map<String, String> expected) {
        UserInput userInput = new UserInput();
        userInput.parseArguments(args);
        Assertions.assertEquals(expected, userInput.getStringArguments());
    }

    @Test
    @DisplayName("handle arguments test")
    void handleArgumentsTest() {
        String[] args = new String[] {"--path", "logs/2023/logs.txt", "--format", "someFormat"};
        UserInput userInput = new UserInput();
        userInput.parseArguments(args);
        userInput.handleArguments();
        Map<String, Object> expected = new HashMap<String, Object>(Map.of(
            "path",
            new ArrayList<Path>(List.of(Path.of("src/main/resources/project3/logs/2023/logs.txt"))),
            "format",
            "someFormat"
        ));
        Assertions.assertEquals(expected, userInput.getArguments());
    }

    @Test
    @DisplayName("negative arguments test")
    void handleNegativeArgumentsTest() {
        String[] args = new String[] {"--path", "incorrectPath", "--format", "someFormat"};
        UserInput userInput = new UserInput();
        userInput.parseArguments(args);
        Assertions.assertThrows(RuntimeException.class, userInput::handleArguments);
    }
}

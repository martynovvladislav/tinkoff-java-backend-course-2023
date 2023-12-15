package edu.hw6;

import edu.hw6.task6.portUtils.PortHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask6 {
    @Test
    @DisplayName("port handler test")
    void portHandlerTest() {
        PortHandler portHandler = new PortHandler();
        Assertions.assertFalse(portHandler.scan().isEmpty());
    }
}

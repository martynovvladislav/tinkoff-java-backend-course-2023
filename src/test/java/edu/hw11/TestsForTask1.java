package edu.hw11;

import edu.hw11.task1.HelloCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask1 {
    @Test
    @DisplayName("hello world object test")
    void helloWorldTest() {
        Assertions.assertEquals(HelloCreator.createHelloObject().toString(), "Hello, ByteBuddy!");
    }
}

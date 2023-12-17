package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.Redefiner;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask2 {
    @Test
    @DisplayName("redefine method test")
    void redefineTest() {
        ByteBuddyAgent.install();
        Redefiner.redefine();
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        Assertions.assertEquals(arithmeticUtils.sum(5, 5), 25);
    }
}

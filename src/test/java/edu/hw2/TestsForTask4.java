package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.CallingInfo.callingInfo;

public class TestsForTask4 {
    @Test
    @DisplayName("get information about the caller of 'Calling info' method")
    void getCallerTest() {
        Assertions.assertThat(new Task4.CallingInfo("edu.hw2.TestsForTask4", "getCallerTest")).isEqualTo(callingInfo());
    }
}

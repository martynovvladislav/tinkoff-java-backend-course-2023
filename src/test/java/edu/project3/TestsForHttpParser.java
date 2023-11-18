package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.parsers.HttpParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

public class TestsForHttpParser {
    @Test
    @DisplayName("send request test")
    void sendRequestTest() {
        List<LogRecord> logs = new ArrayList<>();
        HttpParser httpParser = Mockito.mock(HttpParser.class);
        Mockito.when(httpParser.parseHttp(any())).thenReturn(logs);
        Assertions.assertEquals(logs, httpParser.parseHttp("path"));
    }
}

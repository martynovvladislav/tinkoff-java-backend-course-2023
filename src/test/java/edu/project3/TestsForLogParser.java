package edu.project3;

import edu.project3.logHandlers.LogRecord;
import edu.project3.parsers.DateParser;
import edu.project3.parsers.LogParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForLogParser {
    @Test
    @DisplayName("parse nginx log test")
    void parseLogTest() {
        String nginxLog = "93.180.71.3 - - [29/May/2015:08:05:32 +0000] \"POST /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        LogRecord logRecord = new LogRecord(
            "93.180.71.3",
            "-",
            DateParser.parseDateTime("29/May/2015:08:05:32 +0000"),
            "POST",
            "/downloads/product_1",
            "HTTP/1.1",
            304,
            0,
            "-",
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        );
        Assertions.assertEquals(logRecord, LogParser.parseLog(nginxLog));
    }

    @Test
    @DisplayName("negative log input")
    void parseNegativeLogInputTest() {
        Assertions.assertThrows(RuntimeException.class, () -> LogParser.parseLog("12345"));
    }
}

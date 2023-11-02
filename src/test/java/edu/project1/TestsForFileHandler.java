package edu.project1;

import edu.project1.exceptions.FileMaxAttemptsException;
import edu.project1.exceptions.FileWordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestsForFileHandler {
    @Test
    @DisplayName("Positive word input")
    void positiveWordInput() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\resources\\settings.txt", false));
        writer.write("Enter maxAttempts in the next line:\n");
        writer.write("5\n");
        writer.write("Enter words in the next line:\naPPle\ntomato\ncucumber");
        writer.close();
        FileHandler.getInfoFromFile();
        Assertions.assertEquals(5, FileHandler.getFileMaxAttempts());
        Assertions.assertEquals(new ArrayList<String>(List.of("apple", "tomato", "cucumber")), FileHandler.getFileDictionary());
    }

    @Test
    @DisplayName("Negative maxAttempts input")
    void negativeMaxAttemptsInput() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\resources\\settings.txt", false));
        writer.write("Enter maxAttempts in the next line:\n");
        writer.write("dasdada\n");
        writer.write("Enter words in the next line:\napple\ntomato");
        writer.close();
        Assertions.assertThrows(FileMaxAttemptsException.class, FileHandler::getInfoFromFile);
    }

    @Test
    @DisplayName("Negative word input")
    void negativeWordInput() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\resources\\settings.txt", false));
        writer.write("Enter maxAttempts in the next line:\n");
        writer.write("5\n");
        writer.write("Enter words in the next line:\n123\ntomato");
        writer.close();
        Assertions.assertThrows(FileWordException.class, FileHandler::getInfoFromFile);
    }
}

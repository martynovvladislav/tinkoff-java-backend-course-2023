package edu.project1;

import edu.project1.exceptions.GiveUpException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestsForInputHandler {
    @Test
    @DisplayName("letter positive input test")
    public void test1() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("a\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.getLetter(), 'a');
        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("'user give up' test")
    public void test2() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("!\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertThrows(GiveUpException.class, InputHandler::getLetter);
        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("letter negative input test")
    public void test3() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("abc\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertThrows(RuntimeException.class, InputHandler::getLetter);
        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("letter negative input test")
    public void test4() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("abc\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertThrows(RuntimeException.class, InputHandler::getLetter);
        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("exit positive input test")
    public void test5() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("y\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.getStopLetter(), true);
        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("exit negative input test")
    public void test6() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("f\n".getBytes());
        InputStream originalSystemIn = System.in;
        System.setIn(inputStream);
        Assertions.assertThrows(RuntimeException.class, InputHandler::getStopLetter);
        System.setIn(originalSystemIn);
    }
}

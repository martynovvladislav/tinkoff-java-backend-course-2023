package edu.project1;

import edu.project1.exceptions.AlreadyGuessedLetterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestForUserGuessHandler {
    @Test
    @DisplayName("user's right guess test")
    void rightGuessTest() {
        Session session = new Session("tomato");
        int expectedAttempt = session.getAttempt();
        Assertions.assertTrue(UserGuessHandler.checkUserAnswer(session, 'o'));
        Assertions.assertEquals(String.valueOf(session.getUserAnswer()), "*o***o");
        Assertions.assertEquals(session.getAttempt(), expectedAttempt);
    }

    @Test
    @DisplayName("user's wrong guess test")
    void wrongGuessTest() {
        Session session = new Session("tomato");
        Assertions.assertFalse(UserGuessHandler.checkUserAnswer(session, 'q'));
        Assertions.assertEquals(String.valueOf(session.getUserAnswer()), "******");
    }

    @Test
    @DisplayName("user's same guess test")
    void sameGuessTest() {
        Session session = new Session("tomato");
        session.guess('t');
        Assertions.assertThrows(
            AlreadyGuessedLetterException.class,
            () -> UserGuessHandler.checkUserAnswer(session, 't')
        );
        String actual = String.valueOf(session.getUserAnswer());
        Assertions.assertEquals(actual, "t***t*");
    }

    @Test
    @DisplayName("user guessed the whole word test")
    void wholeWordGuessTest() {
        Session session = new Session("tomato");
        for (char letter : new char[] {'t', 'o', 'm', 'a'}) {
            session.guess(letter);
        }
        Assertions.assertTrue(UserGuessHandler.win(session.getUserAnswer()));
    }
}

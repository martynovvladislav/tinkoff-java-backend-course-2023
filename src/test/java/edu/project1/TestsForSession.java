package edu.project1;

import edu.project1.exceptions.AlreadyGuessedLetterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestsForSession {
    @Test
    @DisplayName("user's right guess test")
    void rightGuessTest() {
        Session session = new Session("apple");
        int lastUserAttempt = session.getAttempt();
        session.guess('a');
        Assertions.assertEquals(lastUserAttempt, session.getAttempt());
        Assertions.assertEquals("a****", session.getWord());
    }

    @Test
    @DisplayName("user's wrong guess test")
    void wrongGuessTest() {
        Session session = new Session("apple");
        int lastUserAttempt = session.getAttempt();
        session.guess('t');
        Assertions.assertEquals(lastUserAttempt + 1, session.getAttempt());
        Assertions.assertEquals("*****", session.getWord());
    }

    @Test
    @DisplayName("user's same guess test")
    void sameGuessTest() {
        Session session = new Session("apple");
        session.guess('a');
        int lastUserAttempt = session.getAttempt();
        String lastUserWord = session.getWord();
        Assertions.assertThrows(AlreadyGuessedLetterException.class, () -> session.guess('a'));
        Assertions.assertEquals(lastUserAttempt, session.getAttempt());
        Assertions.assertEquals(lastUserWord, session.getWord());
    }

    static Arguments[] winArguments() {
        Session session1 = new Session("apple");
        //contains wrong input
        for (char letter : new char[] {'a', 'p', 'l', 'q', 'e'}) {
            session1.guess(letter);
        }

        Session session2 = new Session("tomato");
        //all inputs were right
        for (char letter : new char[] {'t', 'o', 'm', 'a'}) {
            session2.guess(letter);
        }

        return new Arguments[] {
            Arguments.of(session1),
            Arguments.of(session2)
        };
    }

    @ParameterizedTest
    @DisplayName("user won test")
    @MethodSource("winArguments")
    void winTest(Session session) {
        Assertions.assertTrue(session.win());
    }

    static Arguments[] loseArguments() {
        Session session1 = new Session("apple");
        //2 right guesses, 5 wrong guesses
        for (char letter : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'}) {
            session1.guess(letter);
        }
        //all wrong guesses
        Session session2 = new Session("tomato");
        for (char letter : new char[] {'b', 'c', 'd', 'e', 'f'}) {
            session2.guess(letter);
        }

        return new Arguments[] {
            Arguments.of(session1),
            Arguments.of(session2)
        };
    }

    @ParameterizedTest
    @DisplayName("user lost test")
    @MethodSource("loseArguments")
    void loseTest(Session session) {
        Assertions.assertTrue(session.lose());
    }
}

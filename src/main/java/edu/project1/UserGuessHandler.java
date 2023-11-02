package edu.project1;

import edu.project1.exceptions.AlreadyGuessedLetterException;

public class UserGuessHandler {
    private UserGuessHandler() {
    }

    public static boolean checkUserAnswer(Session session, char letter) {
        String answer = session.getAnswer();
        char[] userAnswer = session.getUserAnswer();
        boolean guessedRight = false;
        for (int i = 0; i < userAnswer.length; i++) {
            if (answer.charAt(i) == letter && userAnswer[i] == '*') {
                guessedRight = true;
                userAnswer[i] = letter;
            } else if (answer.charAt(i) == letter && userAnswer[i] == letter) {
                throw new AlreadyGuessedLetterException();
            }
        }
        return guessedRight;
    }

    public static boolean win(char[] userAnswer) {
        int letterCount = 0;
        for (int i = 0; i < userAnswer.length; i++) {
            if (userAnswer[i] != '*') {
                letterCount++;
            }
        }
        if (letterCount == userAnswer.length) {
            return true;
        }
        return false;
    }
}

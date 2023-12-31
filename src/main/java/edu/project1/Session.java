package edu.project1;

import edu.project1.exceptions.AlreadyGuessedLetterException;

public class Session {
    private final String answer;
    private char[] userAnswer;
    private final static int MAX_ATTEMPTS = FileHandler.getFileMaxAttempts();
    private int attempt;

    public Session(String answer) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        for (int i = 0; i < userAnswer.length; i++) {
            userAnswer[i] = '*';
        }
    }

    public String getAnswer() {
        return answer;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public boolean guess(char letter) {
        boolean guessed;
        try {
            guessed = UserGuessHandler.checkUserAnswer(this, letter);
        } catch (AlreadyGuessedLetterException e) {
            throw e;
        }
        if (!guessed) {
            attempt++;
        }
        return guessed;
    }

    public int getAttempt() {
        return attempt;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public String getWord() {
        return String.valueOf(userAnswer);
    }

    public boolean win() {
        return UserGuessHandler.win(getUserAnswer()) && attempt < MAX_ATTEMPTS;
    }

    public boolean lose() {
        return !UserGuessHandler.win(getUserAnswer()) && attempt >= MAX_ATTEMPTS;
    }
}

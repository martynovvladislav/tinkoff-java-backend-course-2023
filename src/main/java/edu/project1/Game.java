package edu.project1;

import edu.project1.exceptions.AlreadyGuessedLetterException;
import edu.project1.exceptions.FileMaxAttemptsException;
import edu.project1.exceptions.FileWordException;
import edu.project1.exceptions.GiveUpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private Session session;

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("checkstyle:ReturnCount")
    public void run() {
        try {
            FileHandler.getInfoFromFile();
        } catch (FileMaxAttemptsException e) {
            LOGGER.info("Wrong maxAttempts input in file");
            return;
        } catch (FileWordException e) {
            LOGGER.info("Wrong word input in file");
            return;
        } catch (RuntimeException e) {
            LOGGER.info("File not found!");
            return;
        }
        LOGGER.info("Hello! This is 'Hangman Game'\n");
        outerloop:
        while (true) {
            session = new Session(WordsDictionary.getWord());
            LOGGER.info("Your word is consist of " + session.getAnswer().length() + " letters");
            while (true) {
                LOGGER.info("Guess a letter(or use '!' to give up):");
                char inputData;
                try {
                    inputData = InputHandler.getLetter();
                } catch (GiveUpException e) {
                    LOGGER.info("You decided to give up!");
                    break outerloop;
                } catch (RuntimeException e) {
                    LOGGER.info("Wrong input, try again!");
                    continue;
                }

                boolean guessResult;
                try {
                    guessResult = session.guess(inputData);
                } catch (AlreadyGuessedLetterException e) {
                    LOGGER.info("You already guessed this letter!");
                    continue;
                }

                if (guessResult) {
                    LOGGER.info("Hit!");
                } else {
                    LOGGER.info("Missed, mistake " + session.getAttempt() + " out of " + session.getMaxAttempts());
                }

                LOGGER.info("The word: " + session.getWord());

                if (session.win()) {
                    LOGGER.info("You won!");
                    break;
                }

                if (session.lose()) {
                    LOGGER.info("You lost!");
                    break;
                }
            }

            while (true) {
                LOGGER.info("Start again? (y/n)");
                try {
                    boolean userStopLetter = InputHandler.getStopLetter();
                    if (!userStopLetter) {
                        LOGGER.info("Was a good game!");
                        break outerloop;
                    } else {
                        LOGGER.info("Running new game...");
                        break;
                    }
                } catch (RuntimeException e) {
                    LOGGER.info("Wrong input, try again!!!");
                }
            }
        }
    }
}

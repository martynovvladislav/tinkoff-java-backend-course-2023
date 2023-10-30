package edu.project1;

import edu.project1.exceptions.GiveUpException;
import java.util.Scanner;

public class InputHandler {
    private InputHandler() {
    }

    public static char getLetter() {
        Scanner scanner = new Scanner(System.in);
        char[] inputData = scanner.nextLine().toCharArray();
        if (inputData.length != 1) {
            throw new RuntimeException();
        } else if (inputData[0] == '!') {
            throw new GiveUpException();
        } else if (inputData[0] >= 'a' && inputData[0] <= 'z') {
            return inputData[0];
        } else {
            throw new RuntimeException();
        }
    }

    public static boolean getStopLetter() {
        Scanner scanner = new Scanner(System.in);
        char[] inputData = scanner.nextLine().toCharArray();
        if (inputData.length != 1) {
            throw new RuntimeException();
        } else if (inputData[0] == 'y') {
            return true;
        } else if (inputData[0] == 'n') {
            return false;
        } else {
            throw new RuntimeException();
        }
    }
}

package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String inputData) {
        if (inputData == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder outputData = new StringBuilder();
        for (int i = 0; i < inputData.length(); i++) {
            if (inputData.charAt(i) >= 'A' && inputData.charAt(i) <= 'Z') {
                outputData.append((char) ('Z' - inputData.charAt(i) + 'A'));
            } else if (inputData.charAt(i) >= 'a' && inputData.charAt(i) <= 'z') {
                outputData.append((char) ('z' - inputData.charAt(i) + 'a'));
            } else {
                outputData.append(inputData.charAt(i));
            }
        }
        return String.valueOf(outputData);
    }
}

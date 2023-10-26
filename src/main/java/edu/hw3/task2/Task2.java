package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String inputSequence) {
        if (inputSequence == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> clustersList = new ArrayList<>();
        StringBuilder currentCluster = new StringBuilder();
        int bracketsBalance = 0;
        for (int i = 0; i < inputSequence.length(); i++) {
            if (inputSequence.charAt(i) == '(') {
                bracketsBalance++;
            } else {
                bracketsBalance--;
            }
            currentCluster.append(inputSequence.charAt(i));

            if (bracketsBalance < 0) {
                throw new InvalidBracketsSequenceException();
            } else if (bracketsBalance == 0) {
                clustersList.add(String.valueOf(currentCluster));
                currentCluster = new StringBuilder();
            }
        }
        if (bracketsBalance != 0) {
            throw new InvalidBracketsSequenceException();
        } else {
            return clustersList;
        }
    }
}

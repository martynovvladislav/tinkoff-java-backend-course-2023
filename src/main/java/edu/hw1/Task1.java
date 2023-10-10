package edu.hw1;

import static java.lang.Integer.parseInt;

public class Task1 {
    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        String[] splitData = time.split(":");
        if (splitData.length < 2) {
            return -1;
        }
        int minutes = parseInt(splitData[0]);
        int seconds = parseInt(splitData[1]);
        final int secondsInMinutes = 60;
        if (seconds >= secondsInMinutes) {
            return -1;
        } else {
            return minutes * secondsInMinutes + seconds;
        }
    }

}

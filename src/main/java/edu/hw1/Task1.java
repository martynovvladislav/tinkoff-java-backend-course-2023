package edu.hw1;

import static java.lang.Integer.parseInt;

public class Task1 {
    private Task1() {
    }

    public static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {
        if (time == null) {
            return -1;
        }
        String[] splitData = time.split(":");
        if (splitData.length < 2) {
            return -1;
        }
        int minutes = parseInt(splitData[0]);
        int seconds = parseInt(splitData[1]);
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        } else {
            return minutes * SECONDS_IN_MINUTE + seconds;
        }
    }

}

package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {}

    public static boolean isSubsequence(String subsequence, String string) {
        StringBuilder regex = new StringBuilder();
        regex.append(".*");
        for (int i = 0; i < subsequence.length(); i++) {
            regex.append(subsequence.charAt(i)).append(".*");
        }
        Pattern pattern = Pattern.compile(String.valueOf(regex));
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}

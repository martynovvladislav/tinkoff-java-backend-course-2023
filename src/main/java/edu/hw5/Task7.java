package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    private Task7() {}

    public static boolean firstRegex(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean secondRegex(String string) {
        Pattern pattern = Pattern.compile("^([01])[01]*([01])$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches() && matcher.group(1).equals(matcher.group(2));
    }

    public static boolean thirdRegex(String string) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}

package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {}

    public static boolean firstRegex(String string) {
        Pattern pattern = Pattern.compile("^[01]([01]{2})*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean secondRegex(String string) {
        Pattern pattern = Pattern.compile("^(0([01]{2})*)|(1([01]{2})*[01])$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean thirdRegex(String string) {
        Pattern pattern = Pattern.compile("^(1*01*01*0)*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean fourthRegex(String string) {
        Pattern pattern = Pattern.compile("^(?!(111|11)$)[01]*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}

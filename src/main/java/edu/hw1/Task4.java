package edu.hw1;

public class Task4 {
    private Task4() {}

    public static String fixString(String broken) {
        if (broken == null) {
            return null;
        }
        StringBuilder fixed = new StringBuilder();
        for (int i = 0; i < broken.length() - 1; i += 2) {
            fixed.append(broken.charAt(i + 1));
            fixed.append(broken.charAt(i));
        }
        if (broken.length() % 2 == 1) {
            fixed.append(broken.charAt(broken.length() - 1));
        }
        return fixed.toString();
    }
}

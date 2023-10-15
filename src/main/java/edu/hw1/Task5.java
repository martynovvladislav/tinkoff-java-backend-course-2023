package edu.hw1;

import static java.lang.Math.abs;

public class Task5 {
    private Task5() {}

    public static boolean isPalindromeDescendant(int inputNum) {
        int num = abs(inputNum);
        String numstr = Integer.toString(num);
        while (numstr.length() > 1) {
            if (isPalindrome(numstr)) {
                return true;
            }
            String temp = "";
            for (int i = 0; i < numstr.length(); i += 2) {
                if (i != numstr.length() - 1) {
                    temp += Integer.toString(Integer.parseInt(String.valueOf(numstr.charAt(i)))
                        + Integer.parseInt(String.valueOf(numstr.charAt(i + 1))));
                } else {
                    temp += Integer.toString(Integer.parseInt(String.valueOf(numstr.charAt(i))));
                }
            }
            numstr = temp;
        }
        return false;
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) == str.charAt(str.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}

package edu.hw1;

public class Task7 {
    private Task7() {}

    public static int rotateRight(int n, int shift) {
        String initBin = Integer.toBinaryString(n);
        char[] ansBin = initBin.toCharArray();
        for (int i = 0; i < initBin.length(); i++) {
            ansBin[(i + shift) % initBin.length()] = initBin.charAt(i);
        }
        return Integer.parseInt(new String(ansBin), 2);
    }

    public static int rotateLeft(int n, int shift) {
        String initBin = Integer.toBinaryString(n);
        char[] ansBin = initBin.toCharArray();
        for (int i = 0; i < initBin.length(); i++) {
            ansBin[(initBin.length() + (i - shift % initBin.length())) % initBin.length()] = initBin.charAt(i);
        }
        return Integer.parseInt(new String(ansBin), 2);
    }

}

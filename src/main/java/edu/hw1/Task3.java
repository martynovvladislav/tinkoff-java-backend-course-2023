package edu.hw1;

public class Task3 {
    private Task3() {}

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null || a1.length == 0 || a2.length == 0) {
            return false;
        }
        int a1Min = a1[0];
        int a2Min = a2[0];
        int a1Max = a1[0];
        int a2Max = a2[0];
        for (int i = 0; i < a1.length; i++) {
            if (a1Min > a1[i]) {
                a1Min = a1[i];
            }
            if (a1Max < a1[i]) {
                a1Max = a1[i];
            }
        }
        for (int i = 0; i < a2.length; i++) {
            if (a2Min > a2[i]) {
                a2Min = a2[i];
            }
            if (a2Max < a2[i]) {
                a2Max = a2[i];
            }
        }
        return a1Max < a2Max && a1Min > a2Min;
    }

}

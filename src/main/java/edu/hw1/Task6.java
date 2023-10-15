package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task6 {
    private Task6() {}

    public static final int KAP_CONST = 6174;
    public static final int RADIX = 10;
    public static final int NUMBER_LENGTH = 4;

    public static int countK(int num) {
        if (num < 0) {
            return -1;
        }
        if (num == KAP_CONST) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int tempNum = num;
        while (tempNum > 0) {
            list.add(tempNum % RADIX);
            tempNum /= RADIX;
        }
        while (list.size() < NUMBER_LENGTH) {
            list.add(0);
        }
        Collections.sort(list, Collections.reverseOrder());
        int ascNum = 0;
        int descNum = 0;
        for (int i = 0; i < list.size(); i++) {
            descNum *= RADIX;
            ascNum *= RADIX;
            descNum += list.get(i);
            ascNum += list.get(list.size() - i - 1);
        }

        return countK(descNum - ascNum) + 1;
    }

}

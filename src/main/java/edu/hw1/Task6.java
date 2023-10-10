package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task6 {
    private Task6() {}

    @SuppressWarnings({"checkstyle: MagicNumber", "checkstyle:MagicNumber"})
    public static int countK(int num) {
        if (num < 0) {
            return -1;
        }
        final int kapConst = 6174;
        final int radix = 10;
        if (num == kapConst) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int tempNum = num;
        while (tempNum > 0) {
            list.add(tempNum % radix);
            tempNum /= radix;
        }
        while (list.size() < 4) {
            list.add(0);
        }
        Collections.sort(list, Collections.reverseOrder());
        int ascNum = 0;
        int descNum = 0;
        for (int i = 0; i < list.size(); i++) {
            descNum *= radix;
            ascNum *= radix;
            descNum += list.get(i);
            ascNum += list.get(list.size() - i - 1);
        }

        return countK(descNum - ascNum) + 1;
    }

}

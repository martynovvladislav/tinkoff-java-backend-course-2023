package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class StringFieldGenerator implements FieldRandomGenerator<String> {
    private static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz01234567890";
    private static final int MAX_SIZE = 100;

    @Override
    public String generate(Random random, Annotation[] annotations) {
        int min = 2;
        int max = MAX_SIZE;
        boolean notNull = false;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = (int) ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = (int) ((Min) annotations[i]).value();
            }
            if (annotations[i] instanceof NotNull) {
                notNull = true;
            }
        }
        if (!notNull) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int size = random.nextInt(min, max);
        for (int i = 0; i < size; i++) {
            builder.append(SYMBOLS.charAt(random.nextInt(0, SYMBOLS.length())));
        }
        return String.valueOf(builder);
    }
}

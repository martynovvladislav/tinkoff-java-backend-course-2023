package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class LongFieldGenerator implements FieldRandomGenerator<Long> {
    @Override
    public Long generate(Random random, Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        boolean notNull = false;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = ((Min) annotations[i]).value();
            }
            if (annotations[i] instanceof NotNull) {
                notNull = true;
            }
        }
        if (notNull) {
            return random.nextLong(min, max);
        }
        return null;
    }
}

package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class LongFieldGenerator implements FieldRandomGenerator<Long> {
    @Override
    public Long generate(Random random, Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = ((Min) annotations[i]).value();
            }
        }
        return  random.nextLong(min, max);
    }
}

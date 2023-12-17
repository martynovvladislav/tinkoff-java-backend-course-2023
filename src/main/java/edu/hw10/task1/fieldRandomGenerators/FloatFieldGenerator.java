package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatFieldGenerator implements FieldRandomGenerator<Float> {

    @Override
    public Float generate(Random random, Annotation[] annotations) {
        double min = Float.MIN_VALUE;
        double max = Float.MAX_VALUE;
        boolean notNull = false;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = (float) ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = (float) ((Min) annotations[i]).value();
            }
            if (annotations[i] instanceof NotNull) {
                notNull = true;
            }
        }
        if (notNull) {
            return (float) random.nextDouble(min, max);
        }
        return null;
    }
}

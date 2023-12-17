package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class DoubleFieldGenerator implements FieldRandomGenerator<Double> {
    @Override
    public Double generate(Random random, Annotation[] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        boolean notNull = false;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = (double) ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = (double) ((Min) annotations[i]).value();
            }
            if (annotations[i] instanceof NotNull) {
                notNull = true;
            }
        }
        if (notNull) {
            return random.nextDouble(min, max);
        }
        return null;
    }
}

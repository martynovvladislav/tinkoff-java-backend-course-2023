package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteFieldGenerator implements FieldRandomGenerator<Byte> {
    @Override
    public Byte generate(Random random, Annotation[] annotations) {
        int min = Byte.MIN_VALUE;
        int max = Byte.MAX_VALUE;
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
        if (notNull) {
            return (byte) random.nextInt(min, max);
        }
        return null;
    }
}

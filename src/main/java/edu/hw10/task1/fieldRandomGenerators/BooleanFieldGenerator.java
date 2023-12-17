package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanFieldGenerator implements FieldRandomGenerator<Boolean> {

    @Override
    public Boolean generate(Random random, Annotation[] annotations) {
        boolean notNull = false;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof NotNull) {
                notNull = true;
            }
        }
        if (notNull) {
            return random.nextBoolean();
        }
        return null;
    }
}

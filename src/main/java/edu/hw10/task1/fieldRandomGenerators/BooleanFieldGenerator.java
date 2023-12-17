package edu.hw10.task1.fieldRandomGenerators;

import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanFieldGenerator implements FieldRandomGenerator<Boolean> {

    @Override
    public Boolean generate(Random random, Annotation[] annotations) {
        return random.nextBoolean();
    }
}

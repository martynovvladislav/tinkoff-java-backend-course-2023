package edu.hw10.task1.fieldRandomGenerators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;

public class IntegerFieldGenerator implements FieldRandomGenerator<Integer> {
    @Override
    public Integer generate(Random random, Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        System.out.println(annotations.length);
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Max) {
                max = (int) ((Max) annotations[i]).value();
            }
            if (annotations[i] instanceof Min) {
                min = (int) ((Min) annotations[i]).value();
            }
        }
        return  random.nextInt(min, max);
    }
}

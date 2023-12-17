package edu.hw10.task1.fieldRandomGenerators;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public interface FieldRandomGenerator<T> {
    static Map<Class<?>, FieldRandomGenerator<?>> getGenerators() {
        Map<Class<?>, FieldRandomGenerator<?>> generatorMap = new HashMap<>();
        generatorMap.put(boolean.class, new BooleanFieldGenerator());
        generatorMap.put(byte.class, new ByteFieldGenerator());
        generatorMap.put(double.class, new DoubleFieldGenerator());
        generatorMap.put(float.class, new FloatFieldGenerator());
        generatorMap.put(int.class, new IntegerFieldGenerator());
        generatorMap.put(long.class, new LongFieldGenerator());
        generatorMap.put(String.class, new StringFieldGenerator());
        generatorMap.put(Boolean.class, new BooleanFieldGenerator());
        generatorMap.put(Byte.class, new ByteFieldGenerator());
        generatorMap.put(Double.class, new DoubleFieldGenerator());
        generatorMap.put(Float.class, new FloatFieldGenerator());
        generatorMap.put(Integer.class, new IntegerFieldGenerator());
        generatorMap.put(Long.class, new LongFieldGenerator());
        return generatorMap;
    }

    T generate(Random random, Annotation[] annotations);
}

package edu.hw10.task1;

import edu.hw10.task1.fieldRandomGenerators.FieldRandomGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random;

    public RandomObjectGenerator(Random random) {
        this.random = random;
    }

    public <T> T nextObject(Class<T> tClass) {
        Constructor<?> constructor = getBiggestConstructor(tClass);
        Object[] parameters = generateParameters(constructor.getParameters());
        try {
            return tClass.cast(constructor.newInstance(parameters));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Cannot create object of class " + tClass.getName());
        }
    }

    public <T> T nextObject(Class<T> tClass, String factoryMethod) {
        Method method = getMethod(tClass, factoryMethod);
        Object[] parameters = generateParameters(method.getParameters());
        try {
            return tClass.cast(method.invoke(null, parameters));
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Cannot create object of "
                + tClass.getName() + " class with method " + method.getName());
        }
    }

    private Constructor<?> getBiggestConstructor(Class<?> tClass) {
        Constructor<?>[] constructors = tClass.getConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("There are no constructors in " + tClass.getName());
        }
        return Arrays.stream(constructors)
            .max(Comparator.comparing(Constructor::getParameterCount))
            .orElseThrow();
    }

    private Object[] generateParameters(Parameter[] parameters) {
        Object[] generatedParameters = new Object[parameters.length];
        for (int i = 0; i < generatedParameters.length; i++) {
            generatedParameters[i] = FieldRandomGenerator.getGenerators().get(parameters[i].getType()).generate(
                random, parameters[i].getAnnotations()
            );
        }
        return generatedParameters;
    }

    private Method getMethod(Class<?> tClass, String name) {
        for (Method method : tClass.getMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        throw new RuntimeException("No such method in " + tClass.getName() + " class");
    }
}

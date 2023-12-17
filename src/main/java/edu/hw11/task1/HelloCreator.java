package edu.hw11.task1;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class HelloCreator {
    private HelloCreator() {
    }

    public static Object createHelloObject() {
        Class<?> helloWorldClass = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(HelloCreator.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        try {
            return helloWorldClass.getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

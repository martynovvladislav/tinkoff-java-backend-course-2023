package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Redefiner {
    private Redefiner() {
    }

    public static void redefine() {
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(MultiplicationClass.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    private final static class MultiplicationClass {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}

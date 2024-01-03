package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    public static final String METHOD_NAME = "name";
    public static final int WARMUP_TIME = 5;
    public static final int MEASUREMENT_TIME = 5;
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambda;

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(WARMUP_TIME))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME))
            .build();
        try {
            new Runner(options).run();
        } catch (RunnerException e) {
            throw new RuntimeException(e);
        }
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Vladislav", "Martynov");
        method = Student.class.getMethod(METHOD_NAME);
        methodHandle = MethodHandles
            .lookup()
            .findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));
        lambda = (Function<Student, String>) LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name;
        blackhole.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole blackhole) throws Throwable {
        String name = (String) methodHandle.invokeExact(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactory(Blackhole blackhole) {
        String name = lambda.apply(student);
        blackhole.consume(name);
    }

    record Student(String name, String surname) {
    }
}

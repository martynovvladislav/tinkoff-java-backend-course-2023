package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

public class TestsForTask1 {
    public static class TestClass {
        public final String stringField;
        public final Integer intField;

        public TestClass(String stringField, @Max(30) @Min(10) int intField) {
            this.intField = intField;
            this.stringField = stringField;
        }

        public static TestClass create(String stringField, @Min(10) @Max(30) int intField) {
            return new TestClass(stringField, intField);
        }
    }

    public record TestRecord(String stringField, @Min(10) @Max(30) Integer intField) {}


    @Test
    @DisplayName("create object of class with constructor")
    void createObjectWithConstructorTest() {
        RandomObjectGenerator generator = new RandomObjectGenerator(ThreadLocalRandom.current());
        TestClass object = generator.nextObject(TestClass.class);
        Assertions.assertNotNull(object.intField);
        Assertions.assertNotNull(object.stringField);
        Assertions.assertTrue(object.intField < 30 && object.intField >= 10);
    }

    @Test
    @DisplayName("create object of class with method")
    void createObjectWithMethodTest() {
        RandomObjectGenerator generator = new RandomObjectGenerator(ThreadLocalRandom.current());
        TestClass object = generator.nextObject(TestClass.class, "create");
        Assertions.assertNotNull(object.intField);
        Assertions.assertNotNull(object.stringField);
        System.out.println(object.intField);
        Assertions.assertTrue(object.intField < 30 && object.intField >= 10);
    }

    @Test
    @DisplayName("create object of record")
    void createObjectOfRecordTest() {
        RandomObjectGenerator generator = new RandomObjectGenerator(ThreadLocalRandom.current());
        TestRecord object = generator.nextObject(TestRecord.class);
        Assertions.assertNotNull(object.intField);
        Assertions.assertNotNull(object.stringField);
        Assertions.assertTrue(object.intField >= 10 && object.intField < 30);
    }
}

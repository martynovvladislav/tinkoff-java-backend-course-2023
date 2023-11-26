package edu.hw7;

import edu.hw7.task3.LockedPersonDatabase;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.SynchronizedPersonDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class TestsForTask3 {

    private static final int THREADS_AMOUNT = 5;

    static Stream<Arguments> databases() {
        return Stream.of(
            Arguments.of(new SynchronizedPersonDatabase()),
            Arguments.of(new LockedPersonDatabase())
        );
    }

    @ParameterizedTest
    @DisplayName("Synchronized and Locked databases test")
    @MethodSource("databases")
    void synchronizedDatabaseTest(PersonDatabase database) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Person person = new Person(1, "Vladislav", "Moscow", "+12345");
        executorService.execute(() -> database.add(person));
        Future<List<Person>> foundByName = executorService.submit(() -> database.findByName("Vladislav"));
        Future<List<Person>> foundByAddress = executorService.submit(() -> database.findByAddress("Moscow"));
        Future<List<Person>> foundByPhone = executorService.submit(() -> database.findByPhone("+12345"));
        executorService.shutdown();
        Assertions.assertTrue(foundByName.get().contains(person));
        Assertions.assertTrue(foundByAddress.get().contains(person));
        Assertions.assertTrue(foundByPhone.get().contains(person));
    }
}

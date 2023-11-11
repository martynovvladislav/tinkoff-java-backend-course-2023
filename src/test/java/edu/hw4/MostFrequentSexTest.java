package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MostFrequentSexTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE)
        ));
        return Stream.of(
            Arguments.of(animals, Animal.Sex.M)
        );
    }

    @ParameterizedTest
    @DisplayName("get most frequent sex in list test")
    @MethodSource("animalsLists")
    void getMostFrequentSexTest(List<Animal> animals, Animal.Sex sex) {
        Assertions.assertEquals(AnimalUtils.getMostFrequentSex(animals), sex);
    }
}

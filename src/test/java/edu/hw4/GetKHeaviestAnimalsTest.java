package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GetKHeaviestAnimalsTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE)
        ));
        List<Animal> threeHeaviestList = new ArrayList<>(List.of(
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE)
        ));
        List<Animal> twoHeaviestList = new ArrayList<>(List.of(
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE)
        ));
        return Stream.of(
            Arguments.of(animals, 3, threeHeaviestList),
            Arguments.of(animals, 2, twoHeaviestList)
        );
    }

    @ParameterizedTest
    @DisplayName("get k heaviest animals test")
    @MethodSource("animalsLists")
    void getKHeaviestAnimalsTest(List<Animal> animals, int k, List<Animal> answerList) {
        Assertions.assertEquals(AnimalUtils.getKHeaviestAnimals(animals, k), answerList);
    }
}

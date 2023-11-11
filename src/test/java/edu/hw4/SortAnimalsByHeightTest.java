package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SortAnimalsByHeightTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE)
        ));
        List<Animal> sortedAnimals = new ArrayList<>(List.of(
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE)
        ));
        return Stream.of(
            Arguments.of(animals, sortedAnimals)
        );
    }

    @ParameterizedTest
    @DisplayName("sorting animals by height")
    @MethodSource("animalsLists")
    void sortAnimalsByHeightTest(List<Animal> animals, List<Animal> sortedAnimals) {
        Assertions.assertEquals(sortedAnimals, AnimalUtils.sortAnimalsByHeight(animals));
    }
}

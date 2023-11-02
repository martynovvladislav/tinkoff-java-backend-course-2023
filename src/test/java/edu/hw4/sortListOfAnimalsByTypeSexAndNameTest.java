package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class sortListOfAnimalsByTypeSexAndNameTest {

    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dogA", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.FALSE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("dogB", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 1, 150, 200, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.F, 2, 400, 112, Boolean.TRUE)
        ));

        List<Animal> sorted = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 1, 150, 200, Boolean.TRUE),
            new Animal("dogA", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.FALSE),
            new Animal("dogB", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.F, 2, 400, 112, Boolean.TRUE)
        ));

        return Stream.of(
            Arguments.of(animals, sorted)
        );
    }

    @ParameterizedTest
    @DisplayName("sort list of animals by type -> sex -> name")
    @MethodSource("animalsLists")
    void sortListOfAnimalsByTypeSexAndNameTest(List<Animal> animals, List<Animal> sorted) {
        Assertions.assertEquals(AnimalUtils.sortListOfAnimalsByTypeSexAndName(animals), sorted);
    }
}

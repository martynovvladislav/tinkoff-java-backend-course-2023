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

public class getSummaryWeightOfEveryTypeOfAnimalsWhoseAgeIsMoreThanKAndLessThanLTest {

    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.FALSE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 2, 10, 50, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 10, 50, Boolean.TRUE)
        ));

        Map<Animal.Type, Integer> mapAnswer = new HashMap<>(Map.of(Animal.Type.CAT, 200, Animal.Type.DOG, 20,
            Animal.Type.BIRD, 112, Animal.Type.SPIDER, 50));

        return Stream.of(
            Arguments.of(animals, 2, 4, mapAnswer)
        );
    }

    @ParameterizedTest
    @DisplayName("get summary weight of every type of animals whose age < k and > l")
    @MethodSource("animalsLists")
    void getSummaryWeightOfEveryTypeOfAnimalsWhoseAgeIsMoreThanKAndLessThanL(List<Animal> animals,
        int k, int l, Map<Animal.Type, Integer> mapAnswer) {
        Assertions.assertEquals(AnimalUtils
            .getSummaryWeightOfEveryTypeOfAnimalsWhoseAgeIsMoreThanKAndLessThanL(animals, k, l), mapAnswer
        );
    }
}

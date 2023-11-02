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

public class getMapOfAnimalTypesTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE),
            new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 3, 10, 5, Boolean.TRUE),
            new Animal("bigfish", Animal.Type.FISH, Animal.Sex.M, 3, 100, 500, Boolean.TRUE)
        ));
        Map<Animal.Type, Integer> answerMap = new HashMap<>(Map.of(Animal.Type.CAT, 1, Animal.Type.DOG, 1,
            Animal.Type.BIRD, 1, Animal.Type.SPIDER, 1, Animal.Type.FISH, 2));
        return Stream.of(
            Arguments.of(animals, answerMap)
        );
    }

    @ParameterizedTest
    @DisplayName("counting animals of each type test")
    @MethodSource("animalsLists")
    void getMapOfAnimalTypesTest(List<Animal> animals, Map<Animal.Type, Integer> answerMap) {
        Assertions.assertEquals(AnimalUtils.getMapOfAnimalTypes(animals), answerMap);
    }
}

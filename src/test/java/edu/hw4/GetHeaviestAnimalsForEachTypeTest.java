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

public class GetHeaviestAnimalsForEachTypeTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 100, Boolean.FALSE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 100, 200, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 2, 50, 200, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 500, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 50, Boolean.TRUE),
            new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE),
            new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 3, 10, 100, Boolean.TRUE),
            new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 3, 10, 5, Boolean.TRUE)
        ));

        Map<Animal.Type, Animal> answerMap = new HashMap<>(Map.of(Animal.Type.CAT,
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 100, 200, Boolean.FALSE),
            Animal.Type.DOG, new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 2, 50, 200, Boolean.TRUE),
            Animal.Type.BIRD, new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 500, Boolean.TRUE),
            Animal.Type.SPIDER, new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 50, Boolean.TRUE),
            Animal.Type.FISH, new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 3, 10, 100, Boolean.TRUE)));
        return Stream.of(
            Arguments.of(animals, answerMap)
        );
    }

    @ParameterizedTest
    @DisplayName("get heaviest animal for each type test")
    @MethodSource("animalsLists")
    void getHeaviestAnimalForEachTypeTest(List<Animal> animals, Map<Animal.Type, Animal> answerMap) {
        Assertions.assertEquals(AnimalUtils.getHeaviestAnimalsForEachType(animals), answerMap);
    }
}

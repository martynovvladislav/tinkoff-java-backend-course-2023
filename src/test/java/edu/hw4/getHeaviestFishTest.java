package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class getHeaviestFishTest {

    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.FALSE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 2, 10, 50, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 50, Boolean.TRUE),
            new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, Boolean.TRUE)
        ));

        List<Animal> animals1 = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 2, 10, 50, Boolean.FALSE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 500, Boolean.FALSE)
        ));

        List<Animal> animals2 = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE)
        ));

        return Stream.of(
            Arguments.of(animals, animals1, animals2,
                new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 500, Boolean.FALSE)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("get heaviest fish in multiple lists")
    @MethodSource("animalsLists")
    void getHeaviestFishTest(List<Animal> animals, List<Animal> animals1, List<Animal> animals2, Animal answer) {
        Assertions.assertEquals(AnimalUtils.getHeaviestFish(animals, animals1, animals2), answer);
    }
}

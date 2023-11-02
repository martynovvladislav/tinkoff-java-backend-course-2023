package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class getAnimalsWhoseNameHasMoreThanTwoWordsTest {
    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat cat cat", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog dog", Animal.Type.DOG, Animal.Sex.F, 4, 200, 20, Boolean.FALSE),
            new Animal("bird bird bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 50, Boolean.TRUE)
        ));

        List<Animal> animalsAnswer = new ArrayList<>(List.of(
            new Animal("cat cat cat", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("bird bird bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE)
        ));

        return Stream.of(
            Arguments.of(animals, animalsAnswer)
        );
    }

    @ParameterizedTest
    @DisplayName("get animals whose name has more than two words test")
    @MethodSource("animalsLists")
    void getAnimalsWhoseNameHasMoreThanTwoWordsTest(List<Animal> animals, List<Animal> animalsAnswer) {
        Assertions.assertEquals(AnimalUtils.getAnimalsWhoseNameHasMoreThanTwoWords(animals), animalsAnswer);
    }
}

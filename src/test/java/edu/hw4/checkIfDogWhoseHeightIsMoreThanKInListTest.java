package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class checkIfDogWhoseHeightIsMoreThanKInListTest {

    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 4, 200, 20, Boolean.FALSE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 50, Boolean.TRUE)
        ));

        return Stream.of(
            Arguments.of(animals, 100, true),
            Arguments.of(animals, 300, false)
        );
    }

    @ParameterizedTest
    @DisplayName("check if dog whose height is more than k in list")
    @MethodSource("animalsLists")
    void checkIfDogWhoseHeightIsMoreThanKInListTest(List<Animal> animals, int k, Boolean includes) {
        Assertions.assertEquals(AnimalUtils.checkIfDogWhoseHeightIsMoreThanKInList(animals, k), includes);
    }
}

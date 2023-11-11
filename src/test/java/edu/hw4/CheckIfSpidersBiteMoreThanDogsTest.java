package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CheckIfSpidersBiteMoreThanDogsTest {

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

        List<Animal> animals2 = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 2, 10, 50, Boolean.FALSE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 10, 50, Boolean.FALSE)
        ));

        List<Animal> noSpidersList = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 200, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 1, 200, 20, Boolean.FALSE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE)
        ));

        List<Animal> noDogsList = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE),
            new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 10, 50, Boolean.FALSE)
        ));

        List<Animal> noBothList = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 150, 200, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 2, 400, 112, Boolean.TRUE),
            new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 150, 200, Boolean.TRUE),
            new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 1, 400, 112, Boolean.TRUE)
        ));

        return Stream.of(
            Arguments.of(animals, true),
            Arguments.of(animals2, false),
            Arguments.of(noSpidersList, false),
            Arguments.of(noDogsList, false),
            Arguments.of(noBothList, false)
        );
    }

    @ParameterizedTest
    @DisplayName("check if spiders bite more than dogs test")
    @MethodSource("animalsLists")
    void checkIfSpidersBiteMoreThanDogsTest(List<Animal> animals, Boolean answer) {
        Assertions.assertEquals(AnimalUtils.checkIfSpidersBiteMoreThanDogs(animals), answer);
    }
}

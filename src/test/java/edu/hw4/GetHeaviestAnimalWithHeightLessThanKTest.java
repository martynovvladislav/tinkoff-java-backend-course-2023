package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GetHeaviestAnimalWithHeightLessThanKTest {

    public static Stream<Arguments> animalsLists() {
        List<Animal> animals = new ArrayList<>(List.of(
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 1, 100, 10, Boolean.FALSE),
            new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 50, 20, Boolean.TRUE),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE),
            new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 5, Boolean.TRUE)
        ));
        return Stream.of(
            Arguments.of(animals, 50,
                new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 10, 10, 100, Boolean.TRUE)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("get heaviest animal with height is less than k")
    @MethodSource("animalsLists")
    void getHeaviestAnimalWithHeightLessThanK(List<Animal> animals, int k, Animal animal) {
        Assertions.assertEquals(AnimalUtils.getHeaviestAnimalWithHeightLessThanK(animals, k).orElse(null), animal);
    }
}

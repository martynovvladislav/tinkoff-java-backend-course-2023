package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAnimalValidationsListTest {

    @Test
    void getAnimalValidationsList() {
        Animal nameInvalidAnimal = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 10, 10, Boolean.FALSE);
        Animal ageInvalidAnimal = new Animal("dog", Animal.Type.DOG, Animal.Sex.M, -1, 10, 10, Boolean.FALSE);
        Animal allInvalidAnimal = new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, -10, -10, -10, Boolean.FALSE);
        Animal validAnimal = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.M, 5, 30, 10, Boolean.FALSE);
        List<Animal> animals = new ArrayList<>(List.of(nameInvalidAnimal, allInvalidAnimal, ageInvalidAnimal, validAnimal));
        List<String> animalsValidationList = new ArrayList<>(List.of(
            "[Ошибка в параметре животного: имя]",
            "[Ошибка в параметре животного: рост, Ошибка в параметре животного: возраст, Ошибка в параметре животного: вес]",
            "[Ошибка в параметре животного: возраст]",
            "[]"
        ));
        int index = 0;
        Map<String, String> expected = AnimalUtils.getAnimalValidationsList(animals);
        for (Map.Entry<String, String> entry : expected.entrySet()) {
            Assertions.assertEquals(entry.getValue(), animalsValidationList.get(index));
            index++;
        }
    }
}

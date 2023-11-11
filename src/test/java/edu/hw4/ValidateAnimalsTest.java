package edu.hw4;

import edu.hw4.validations.NegativeAgeValidationError;
import edu.hw4.validations.NegativeHeightValidationError;
import edu.hw4.validations.NegativeNameValidationError;
import edu.hw4.validations.NegativeWeightValidationError;
import edu.hw4.validations.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateAnimalsTest {

    @Test
    @DisplayName("validate animals test")
    void validateAnimalsTest() {
        Animal nameInvalidAnimal = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 10, 10, Boolean.FALSE);
        Animal ageInvalidAnimal = new Animal("dog", Animal.Type.DOG, Animal.Sex.M, -1, 10, 10, Boolean.FALSE);
        Animal allInvalidAnimal = new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, -10, -10, -10, Boolean.FALSE);
        Animal validAnimal = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.M, 5, 30, 10, Boolean.FALSE);
        List<Animal> animals = new ArrayList<>(List.of(nameInvalidAnimal, allInvalidAnimal, ageInvalidAnimal, validAnimal));
        Map<String, HashSet<ValidationError>> validationErrors = new HashMap<>(
            Map.of(
                nameInvalidAnimal.name(), new HashSet<>(Set.of(new NegativeNameValidationError())),
                ageInvalidAnimal.name(), new HashSet<>(Set.of(new NegativeAgeValidationError())),
                allInvalidAnimal.name(), new HashSet<>(Set.of(new NegativeHeightValidationError(), new NegativeWeightValidationError(),
                    new NegativeAgeValidationError())),
                validAnimal.name(), new HashSet<>(Set.of())
            ));
        Assertions.assertEquals(new HashMap<>(AnimalUtils.validateAnimals(animals)), validationErrors);
    }
}

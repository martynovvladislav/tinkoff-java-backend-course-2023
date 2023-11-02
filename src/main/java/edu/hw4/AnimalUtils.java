package edu.hw4;

import edu.hw4.validations.NegativeAgeValidationError;
import edu.hw4.validations.NegativeHeightValidationError;
import edu.hw4.validations.NegativeNameValidationError;
import edu.hw4.validations.NegativeWeightValidationError;
import edu.hw4.validations.ValidationError;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimalUtils {

    private AnimalUtils() {}

    private static final int DANGEROUS_HEIGHT = 100;

    public static List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).toList();
    }

    public static List<Animal> getKHeaviestAnimals(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> getMapOfAnimalTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(o -> 1)));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(animal -> animal.name().length()))
            .toList()
            .get(animals.size() - 1);
    }

    public static Animal.Sex getMostFrequentSex(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.summingInt(o -> 1)))
            .entrySet()
            .stream().max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElseThrow();
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimalsForEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal getKthOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1)
            .findFirst().orElseThrow();
    }

    public static Optional<Animal> getHeaviestAnimalWithHeightLessThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countAnimalPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getAnimalsWhoseAgeIsNotEqualsToNumberOfPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    public static List<Animal> getAnimalsWhoCanBiteAndWhoseHeightIsMoreThan100(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > DANGEROUS_HEIGHT).toList();
    }

    public static List<Animal> getAnimalsWhoseWeightIsMoreThanHeight(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.height() < animal.weight()).toList();
    }

    public static List<Animal> getAnimalsWhoseNameHasMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean checkIfDogWhoseHeightIsMoreThanKInList(List<Animal> animals, int k) {
        return animals.stream().anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    public static Map<Animal.Type, Integer>
    getSummaryWeightOfEveryTypeOfAnimalsWhoseAgeIsMoreThanKAndLessThanL(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortListOfAnimalsByTypeSexAndName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean checkIfSpidersBiteMoreThanDogs(List<Animal> animals) {
        long spiderCount = animals.stream()
            .filter(animal -> animal.type()
                .equals(Animal.Type.SPIDER))
            .count();

        long dogCount = animals.stream()
            .filter(animal -> animal.type()
                .equals(Animal.Type.SPIDER))
            .count();

        if (dogCount == 0 || spiderCount == 0) {
            return false;
        }

        long bitingSpidersCount = animals.stream()
            .filter(animal -> animal.type()
                .equals(Animal.Type.SPIDER) && animal.bites())
            .count();

        long bitingDogsCount = animals.stream()
            .filter(animal -> animal.type()
                .equals(Animal.Type.DOG) && animal.bites())
            .count();

        return bitingSpidersCount > bitingDogsCount;
    }

    public static Animal getHeaviestFish(List<Animal>... animalLists) {
        Animal heaviestFish = null;

        for (List<Animal> animals : animalLists) {
            Animal currentHeaviestFish = animals.stream()
                .filter(animal -> animal.type().equals(Animal.Type.FISH))
                .max(Comparator.comparing(Animal::weight)).orElse(null);
            if (currentHeaviestFish != null && heaviestFish == null) {
                heaviestFish = currentHeaviestFish;
            } else if (currentHeaviestFish != null && currentHeaviestFish.weight() > heaviestFish.weight()) {
                heaviestFish = currentHeaviestFish;
            }
        }

        return heaviestFish;
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        List<ValidationError> validationErrors = new ArrayList<>(List.of(
            new NegativeNameValidationError(),
            new NegativeAgeValidationError(),
            new NegativeHeightValidationError(),
            new NegativeWeightValidationError()
        ));
        return validationErrors.stream()
            .filter(validationError -> validationError.checkIfValid(animal))
            .collect(Collectors.toSet());
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalUtils::validateAnimal
            ));
    }

    public static Map<String, String> getAnimalValidationsList(List<Animal> animals) {
        return validateAnimals(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, stringSetEntry -> stringSetEntry.getValue().toString()));
    }
}

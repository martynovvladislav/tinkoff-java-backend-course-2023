package edu.hw4.validations;

import edu.hw4.Animal;
import java.util.Objects;

public class NegativeWeightValidationError extends ValidationError {

    private static final String CAUSE = "вес";

    public NegativeWeightValidationError() {
        super(CAUSE);
    }

    @Override
    public boolean isValid(Animal animal) {
        return animal.weight() < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CAUSE);
    }
}

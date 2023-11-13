package edu.hw4.validations;

import edu.hw4.Animal;
import java.util.Objects;

public class NegativeHeightValidationError extends ValidationError {

    public static final String CAUSE = "рост";

    public NegativeHeightValidationError() {
        super(CAUSE);
    }

    @Override
    public boolean isValid(Animal animal) {
        return animal.height() < 0;
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

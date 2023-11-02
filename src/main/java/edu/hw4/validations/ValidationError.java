package edu.hw4.validations;

import edu.hw4.Animal;

public abstract class ValidationError {
    public final String cause;

    public ValidationError(String cause) {
        this.cause = cause;
    }

    public abstract boolean checkIfValid(Animal animal);

    @Override public String toString() {
        return cause;
    }
}

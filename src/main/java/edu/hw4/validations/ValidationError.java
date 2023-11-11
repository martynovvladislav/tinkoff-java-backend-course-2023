package edu.hw4.validations;

import edu.hw4.Animal;

public abstract class ValidationError {
    private final String cause;

    private static final String INIT_CAUSE = "Ошибка в параметре животного: ";

    public ValidationError(String cause) {
        this.cause = INIT_CAUSE + cause;
    }

    public abstract boolean isValid(Animal animal);

    @Override public String toString() {
        return cause;
    }
}

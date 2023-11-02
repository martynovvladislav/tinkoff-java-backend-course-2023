package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class TestsForTask5 {
    static Arguments[] wrongListInputs() {
        return new Arguments[]{
            Arguments.of(new ArrayList<>(), "ASC"),
            Arguments.of(null, "DESC")
        };
    }

    @ParameterizedTest
    @DisplayName("negative list inputs test")
    @MethodSource("wrongListInputs")
    void negativeListInputTest(List<String> contactStrings, String order) {
        Assertions.assertEquals(Task5.parseContacts(contactStrings, order), new ArrayList<>());
    }

    static Arguments[] wrongOrderInputs() {
        return new Arguments[]{
            Arguments.of(new ArrayList<>(List.of("ABC DEF")), null),
            Arguments.of(new ArrayList<>(List.of("ABC DEF")), "ASDAA")
        };
    }

    @ParameterizedTest
    @DisplayName("wrong order test")
    @MethodSource("wrongOrderInputs")
    void negativeOrderInputTest(List<String> contactStrings, String order) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(contactStrings, order));
    }

    static Arguments[] positiveInput() {
        return new Arguments[]{
            Arguments.of(new ArrayList<>(List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes")),
                "ASC", new ArrayList<>(List.of(new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"), new Contact("David", "Hume"),
                    new Contact("John", "Locke")))),
            Arguments.of(new ArrayList<>(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss")),
                "DESC", new ArrayList<>(List.of(new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"), new Contact("Paul", "Erdos"))))
        };
    }

    @ParameterizedTest
    @DisplayName("positive input test")
    @MethodSource("positiveInput")
    void positiveInputTest(List<String> contactStrings, String order, List<Contact> contactList) {
        Assertions.assertEquals(Task5.parseContacts(contactStrings, order), contactList);
    }

    @Test
    @DisplayName("incomplete contact test")
    void incompleteContactsDataTest() {
        List<String> contactStrings = new ArrayList<>(List.of("John Doe", "Vladislav", "Alex Alex"));
        Assertions.assertEquals(Task5.parseContacts(contactStrings, "ASC"), new ArrayList<>(
            List.of(new Contact("Alex", "Alex"), new Contact("John", "Doe"),
                new Contact("Vladislav", null))));
    }
}

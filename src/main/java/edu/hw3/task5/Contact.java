package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private String name;
    private String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static Contact parseContactFromString(String fullName) {
        String[] data = fullName.split(" ");
        if (data.length == 1) {
            return new Contact(data[0], null);
        } else {
            return new Contact(data[0], data[1]);
        }
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (this.surname == null || o.surname == null || this.surname.equals(o.surname)) {
            return this.name.compareTo(o.name);
        } else {
            return this.surname.compareTo(o.surname);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Contact contact = (Contact) obj;
        return Objects.equals(this.name, contact.name) && Objects.equals(this.surname, contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}

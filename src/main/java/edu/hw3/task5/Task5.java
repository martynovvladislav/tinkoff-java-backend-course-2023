package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    private final static String ASC_ORDER = "ASC";
    private final static String DESC_ORDER = "DESC";

    public static List<Contact> parseContacts(List<String> contactStrings, String order) {
        if (order == null || (!order.equals(ASC_ORDER) && !order.equals(DESC_ORDER))) {
            throw new IllegalArgumentException();
        }
        if (contactStrings == null || contactStrings.size() == 0) {
            return new ArrayList<>();
        }
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < contactStrings.size(); i++) {
            contacts.add(Contact.parseContactFromString(contactStrings.get(i)));
        }

        if (order.equals(ASC_ORDER)) {
            Collections.sort(contacts);
        } else {
            Collections.sort(contacts, Collections.reverseOrder());
        }

        return contacts;
    }
}

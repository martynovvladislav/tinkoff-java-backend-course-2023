package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> cachedIds = new HashMap<>();
    private final Map<String, List<Person>> cachedNames = new HashMap<>();
    private final Map<String, List<Person>> cachedAddresses = new HashMap<>();
    private final Map<String, List<Person>> cachedPhones = new HashMap<>();

    @Override
    public void add(Person person) {
        cachedIds.put(person.id(), person);
        if (cachedNames.containsKey(person.name())) {
            List<Person> current = cachedNames.get(person.name());
            current.add(person);
            cachedNames.put(person.name(), current);
        } else {
            List<Person> newList = new ArrayList<>();
            newList.add(person);
            cachedNames.put(person.name(), newList);
        }

        if (cachedAddresses.containsKey(person.address())) {
            List<Person> current = cachedAddresses.get(person.address());
            current.add(person);
            cachedAddresses.put(person.address(), current);
        } else {
            List<Person> newList = new ArrayList<>();
            newList.add(person);
            cachedAddresses.put(person.address(), newList);
        }

        if (cachedPhones.containsKey(person.phoneNumber())) {
            List<Person> current = cachedPhones.get(person.phoneNumber());
            current.add(person);
            cachedPhones.put(person.phoneNumber(), current);
        } else {
            List<Person> newList = new ArrayList<>();
            newList.add(person);
            cachedPhones.put(person.phoneNumber(), newList);
        }
    }

    @Override
    public void delete(int id) {
        Person personToRemove = cachedIds.remove(id);
        if (personToRemove == null) {
            return;
        }
        cachedNames.remove(personToRemove.name());
        cachedAddresses.remove(personToRemove.address());
        cachedPhones.remove(personToRemove.phoneNumber());
    }

    @Override
    public List<Person> findByName(String name) {
        if (cachedNames.containsKey(name)) {
            return cachedNames.get(name);
        }
        return null;
    }

    @Override
    public List<Person> findByAddress(String address) {
        if (cachedAddresses.containsKey(address)) {
            return cachedAddresses.get(address);
        }
        return null;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        if (cachedPhones.containsKey(phone)) {
            return cachedPhones.get(phone);
        }
        return null;
    }
}

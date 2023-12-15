package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SingleThreadPasswordSolver {
    private final Map<String, String> database = new HashMap<>();
    private final Map<String, String> foundPasswords = new HashMap<>();
    private final String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final static int MAX_LENGTH = 4;

    public void readDatabase(String data) {
        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] parts = line.split("\\s+");

            if (parts.length == 2) {
                database.put(parts[1], parts[0]);
            }
        }
    }

    private String hashStringMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());

            byte[] digest = md.digest();

            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @SuppressWarnings("checkstyle:ReturnCount")
    public void generatePasswords(String currentPassword) {
        if (database.isEmpty()) {
            return;
        }
        checkPassword(currentPassword);
        if (currentPassword.length() >= MAX_LENGTH) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(currentPassword);
        for (int i = 0; i < symbols.length(); i++) {
            stringBuilder.append(symbols.charAt(i));
            generatePasswords(String.valueOf(stringBuilder));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    private void checkPassword(String password) {
        String hashcode = hashStringMD5(password);
        if (database.containsKey(hashcode)) {
            foundPasswords.put(database.get(hashcode), password);
            database.remove(hashcode);
        }
    }

    public Map<String, String> getFoundPasswords() {
        return foundPasswords;
    }
}

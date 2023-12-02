package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SingleThreadPasswordSolver {
    private final Map<String, String> database = new HashMap<>();
    private final Map<String, String> foundPasswords = new HashMap<>();
    private String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

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
            e.printStackTrace();
            return null;
        }
    }

    private void generatePasswords(String cur) {
        if (database.isEmpty()) {
            return;
        }
        checkPassword(cur);
        if (cur.length() >= 4) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(cur);
        for (int i = 0; i < symbols.length(); i++) {
            stringBuilder.append(symbols.charAt(i));
            generatePasswords(String.valueOf(stringBuilder));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    private void checkPassword(String password) {
        String hashcode = hashStringMD5(password);
        //System.out.println(hashcode);
        if (database.containsKey(hashcode)) {
            foundPasswords.put(database.get(hashcode), password);
            database.remove(hashcode);
        }
    }

    public static void main(String[] args) {
        String data = "a.v.petrov  02c425157ecd32f259548b33402ff6d3\n";
        SingleThreadPasswordSolver solver = new SingleThreadPasswordSolver();
        solver.readDatabase(data);
        long x = System.currentTimeMillis();
        solver.generatePasswords("");
        x -= System.currentTimeMillis();
        System.out.println(x);
        System.out.println(solver.foundPasswords);
        System.out.println(solver.database);
    }
}

package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadPasswordSolver {
    private static final Map<String, String> DATABASE = new HashMap<>();
    private static CountDownLatch latch;
    public static final Map<String, String> FOUND_PASSWORDS = new HashMap<>();
    private static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private static final int MAX_LENGTH = 4;

    public static String hashStringMD5(String input) {
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

    public void readDatabase(String data) {
        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] parts = line.split("\\s+");

            if (parts.length == 2) {
                DATABASE.put(parts[1], parts[0]);
            }
        }
        latch = new CountDownLatch(DATABASE.size());
    }

    public void generatePasswords() {
        EXECUTOR_SERVICE.submit(new SolvingThread(""));
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private record SolvingThread(String currentPassword) implements Callable<Void> {

        @Override
        public Void call() {
            if (!DATABASE.isEmpty()) {
                String hash = hashStringMD5(currentPassword);
                if (DATABASE.containsKey(hash)) {
                    FOUND_PASSWORDS.put(DATABASE.get(hash), currentPassword);
                    DATABASE.remove(hash);
                    latch.countDown();
                }
                if (currentPassword.length() >= MAX_LENGTH) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder(currentPassword);
                for (int i = 0; i < SYMBOLS.length(); i++) {
                    stringBuilder.append(SYMBOLS.charAt(i));
                    EXECUTOR_SERVICE.submit(new SolvingThread(String.valueOf(stringBuilder)));
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
            return null;
        }
    }
}

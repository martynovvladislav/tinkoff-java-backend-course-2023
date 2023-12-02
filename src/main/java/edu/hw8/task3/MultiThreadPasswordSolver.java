package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadPasswordSolver {
    private static final Map<String, String> database = new HashMap<>(Map.of(
        "02c425157ecd32f259548b33402ff6d3", "v.v."
    ));
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static final Map<String, String> foundedPasswords = new HashMap<>();
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final ExecutorService executorService = Executors.newFixedThreadPool(8);


    public static String getHashMD5(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(password.getBytes());
        byte[] md5Bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : md5Bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long x = System.currentTimeMillis();
        System.out.println(new MultiThreadPasswordSolver().bruteForce());
        x -= System.currentTimeMillis();
        System.out.println(x);
    }
    public Map<String, String> bruteForce() {
        executorService.execute(new myThread(""));
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return foundedPasswords;
    }

    private record myThread(String currentPassword) implements Runnable {

        @Override
        public void run() {
            if (!database.isEmpty()) {
                String hash = getHashMD5(currentPassword);
                if (database.containsKey(hash)) {
                    foundedPasswords.put(database.get(hash), currentPassword);
                    database.remove(hash);
                    latch.countDown();
                }
                if (currentPassword.length() >= 4) {
                    return;
                }
                for (char symbol : alphabet.toCharArray()) {
                    executorService.execute(new myThread(currentPassword + symbol));
                }
            } else {
                Thread.currentThread().interrupt();
            }

        }
    }
}

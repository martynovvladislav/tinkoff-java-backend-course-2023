package edu.project1;

import edu.project1.exceptions.FileMaxAttemptsException;
import edu.project1.exceptions.FileWordException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    @SuppressWarnings("checkstyle:MagicNumber")
    private static int fileMaxAttempts = 5;
    private static ArrayList<String> fileDictionary = new ArrayList<>();

    private FileHandler() {}

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void getInfoFromFile() {
        try {
            Scanner scanner = new Scanner(new File("src\\main\\resources\\settings.txt"));
            int counter = 1;
            while (scanner.hasNextLine()) {
                switch (counter) {
                    case 1, 3:
                        scanner.nextLine();
                        break;
                    case 2:
                        try {
                            fileMaxAttempts = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            throw new FileMaxAttemptsException();
                        }
                    default:
                        String word = isValid(scanner.nextLine());
                        if (word == null) {
                            throw new FileWordException();
                        }
                        fileDictionary.add(word);
                }
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        if (fileDictionary.isEmpty()) {
            throw new FileWordException();
        }
        if (fileMaxAttempts <= 0) {
            throw new FileMaxAttemptsException();
        }
    }

    public static String isValid(String word) {
        StringBuilder processedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                processedWord.append(Character.toLowerCase(word.charAt(i)));
            } else if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                processedWord.append(word.charAt(i));
            } else {
                return null;
            }
        }
        if (!processedWord.isEmpty()) {
            return String.valueOf(processedWord);
        }
        return null;
    }

    public static int getFileMaxAttempts() {
        return fileMaxAttempts;
    }

    public static ArrayList<String> getFileDictionary() {
        return fileDictionary;
    }

    public static void setFileMaxAttempts(int fileMaxAttempts) {
        FileHandler.fileMaxAttempts = fileMaxAttempts;
    }
}

package edu.project1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public final class WordsDictionary {
    private WordsDictionary() {}

    private static final ArrayList<String> WORDS = new ArrayList<>(Arrays.asList("apple", "lemon", "orange", "tomato"));

    @NotNull
    public static String getWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }
}

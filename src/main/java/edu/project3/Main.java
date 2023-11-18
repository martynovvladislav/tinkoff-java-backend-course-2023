package edu.project3;

import java.util.Arrays;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);
        Controller.run(args);
    }
}

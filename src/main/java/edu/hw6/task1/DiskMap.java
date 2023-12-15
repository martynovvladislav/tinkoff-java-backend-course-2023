package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DiskMap extends HashMap<String, String> implements Map<String, String> {
    private static final String FILE_PATH = "src/main/resources/hw6/task1/data.txt";

    public void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry entry : this.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            this.clear();
            reader.lines()
                .map(value -> value.split(":"))
                .forEach(value -> this.put(value[0], value[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

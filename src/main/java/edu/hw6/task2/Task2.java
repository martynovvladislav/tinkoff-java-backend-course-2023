package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {}

    public static void cloneFile(Path path) {
        if (!Files.exists(path)) {
            throw new FileDoesNotExistException();
        }

        Path directory = path.getParent();
        int dotIndex = path.getFileName().toString().lastIndexOf('.');
        String filename;
        String extension;
        if (dotIndex == -1) {
            filename = path.getFileName().toString();
            extension = "";
        } else {
            filename = path.getFileName().toString().substring(0, dotIndex);
            extension = path.getFileName().toString().substring(dotIndex);
        }

        String newFilename = filename;
        int copyNumber = 1;
        while (Files.exists(directory.resolve(newFilename + extension))) {
            if (copyNumber == 1) {
                newFilename = filename + " - копия";
            } else {
                newFilename = filename + " - копия (" + copyNumber + ")";
            }
            copyNumber++;
        }

        Path newPath = directory.resolve(newFilename + extension);
        try {
            Files.copy(path, newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

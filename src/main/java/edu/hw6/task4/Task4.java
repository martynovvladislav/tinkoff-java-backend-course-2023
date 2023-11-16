package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {}

    private static final String OUT_DATA = "Programming is learned by writing programs. ― Brian Kernighan";
    private static final Path FILE_PATH = Path.of("src/main/resources/hw6/task4/data.txt");

    public static void createOutputStreamChain() {
        try (OutputStream outputStream = Files.newOutputStream(FILE_PATH);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                bufferedOutputStream, StandardCharsets.UTF_8.newEncoder()
            );
            PrintWriter writer = new PrintWriter(outputStreamWriter)) {
                writer.println(OUT_DATA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

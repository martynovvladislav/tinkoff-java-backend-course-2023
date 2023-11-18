package edu.project3.parsers;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class PathParser {
    private PathParser() {}

    public static List<Path> parseFilePaths(String stringPath) {
        List<Path> matchedFiles = new ArrayList<>();
        Path dir = Path.of("src/main/resources/project3/");
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + "**/" + stringPath + "*");
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    //System.out.println(file.getFileName());
                    if (pathMatcher.matches(file)) {
                        matchedFiles.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                 }
            });
        } catch (IOException e) {
            throw new RuntimeException("IO Exception in Path Parser");
        }
        //matchedFiles.forEach(System.out::println);
        return matchedFiles;
    }

}

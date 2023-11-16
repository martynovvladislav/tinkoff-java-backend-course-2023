package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path entry);

    default AbstractFilter and(AbstractFilter additionalFilter) {
        return entry -> this.accept(entry) && additionalFilter.accept(entry);
    }

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try {
                byte[] buffer = Files.readAllBytes(entry);
                if (buffer.length >= magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if ((byte) magicBytes[i] != buffer[i]) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return pathMatcher.matches(entry.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> Pattern.compile(regex).matcher(entry.getFileName().toString()).find();
    }
}

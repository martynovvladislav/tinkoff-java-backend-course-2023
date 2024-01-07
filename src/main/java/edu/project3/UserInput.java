package edu.project3;

import edu.project3.parsers.DateParser;
import edu.project3.parsers.PathParser;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class UserInput {

    private final static String PATH_OPTION = "path";
    private final static String FROM_OPTION = "from";
    private final static String TO_OPTION = "to";
    private final static String FORMAT_OPTION = "format";
    private Map<String, String> stringArguments;
    private Map<String, Object> arguments;

    public void parseArguments(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder()
            .longOpt(PATH_OPTION)
            .desc("Path to logs file")
            .build());

        options.addOption(Option.builder()
            .longOpt(FROM_OPTION)
            .desc("Time period from")
            .build());

        options.addOption(Option.builder()
            .longOpt(TO_OPTION)
            .desc("Time period to")
            .build());

        options.addOption(Option.builder()
            .longOpt(FORMAT_OPTION)
            .desc("Output format")
            .build());
        CommandLineParser parser = new DefaultParser();
        stringArguments = new HashMap<>();
        try {
            CommandLine line = parser.parse(options, args);
            for (int i = 0; i < line.getOptions().length; i++) {
                stringArguments.put(line.getOptions()[i].getLongOpt(), line.getArgs()[i]);
            }
        } catch (ParseException e) {
            throw new RuntimeException("ParseException while parsing command line");
        }
    }

    public void handleArguments() {
        arguments = new HashMap<>();
        if (stringArguments.containsKey(PATH_OPTION)) {
            if (stringArguments.get(PATH_OPTION).startsWith("http")) {
                arguments.put(PATH_OPTION, stringArguments.get(PATH_OPTION));
                if (URI.create(stringArguments.get(PATH_OPTION)).getPath() == null) {
                    throw new RuntimeException();
                }
            } else {
                try {
                    List<Path> paths = PathParser.parseFilePaths(stringArguments.get(PATH_OPTION));
                    if (paths.isEmpty()) {
                        throw new RuntimeException("Files were not found");
                    }
                    arguments.put(PATH_OPTION, paths);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            throw new RuntimeException("No path in console arguments");
        }

        if (stringArguments.containsKey(FROM_OPTION)) {
            arguments.put(FROM_OPTION, DateParser.parseDate(stringArguments.get(FROM_OPTION)));
            if (arguments.get(FROM_OPTION) == null) {
                arguments.put(FROM_OPTION, DateParser.parseDateTime(stringArguments.get(FROM_OPTION)));
            }
            if (arguments.get(FROM_OPTION) == null) {
                arguments.remove(FROM_OPTION);
            }
        }

        if (stringArguments.containsKey(TO_OPTION)) {
            arguments.put(TO_OPTION, DateParser.parseDate(stringArguments.get(TO_OPTION)));
            if (arguments.get(TO_OPTION) == null) {
                arguments.put(TO_OPTION, DateParser.parseDateTime(stringArguments.get(TO_OPTION)));
            }
            if (arguments.get(TO_OPTION) == null) {
                arguments.remove(TO_OPTION);
            }
        }

        if (stringArguments.containsKey(FORMAT_OPTION)) {
            arguments.put(FORMAT_OPTION, stringArguments.get(FORMAT_OPTION));
        }
        //arguments.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public Map<String, String> getStringArguments() {
        return stringArguments;
    }
}

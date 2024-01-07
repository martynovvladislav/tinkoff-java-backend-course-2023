package edu.project3.outputGenerators;

import java.util.Map;

public interface OutputGenerator {
    String FILE = "src/main/java/edu/project3/output/";

    void generate(Map<String, String>... maps);
}

package edu.project3.outputGenerators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AdocOutputGenerator implements OutputGenerator {
    private static final String TABLE_BORDER = "[options=\"header\"]\n|=====\n";

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void generate(Map<String, String>... maps) {
        try (FileWriter writer = new FileWriter(FILE + "output.adoc")) {

            writer.write("==== Общая информация\n\n");
            writer.write(TABLE_BORDER);
            writer.write("| Метрика | Значение\n");
            writeTable(writer, maps[0], 2);


            writer.write("\n==== Запрашиваемые ресурсы\n\n");
            writer.write(TABLE_BORDER);
            writer.write("| Ресурс | Количество\n");
            writeTable(writer, maps[1], 2);

            writer.write("\n==== Коды ответа\n\n");
            writer.write(TABLE_BORDER);
            writer.write("| Код | Имя | Количество\n");
            writeTable(writer, maps[2], 3);

            writer.write("\n==== HTTP методы\n\n");
            writer.write(TABLE_BORDER);
            writer.write("| Метод | Количество\n");
            writeTable(writer, maps[3], 2);

            writer.write("\n==== HTTP агенты (топ 5)\n\n");
            writer.write(TABLE_BORDER);
            writer.write("| Агент | Количество\n");
            writeTable(writer, maps[4], 2);
        } catch (IOException e) {
            throw new RuntimeException("IO exception in Adoc Generator@!");
        }
    }

    private static void writeTable(FileWriter writer, Map<String, String> data, int tableSize) throws IOException {
        for (Map.Entry entry : data.entrySet()) {
            writer.write("| " + entry.getKey() + " | " + entry.getValue() + "\n");
        }
        writer.write("|=====\n");
    }
}

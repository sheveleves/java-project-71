package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.util.Map;

import static hexlet.code.FileUtils.getPathFile;
import static hexlet.code.FileUtils.readFileToString;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String file1 = readFileToString(filepath1);
        String file2 = readFileToString(filepath2);

        String pathFile = getPathFile(filepath1);
        Map<String, Property> diff = Parser.compareData(file1, file2, pathFile);

        return Formatter.formatter(format, diff);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}

package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.Map;
import static hexlet.code.FileUtils.readFileToString;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String file1 = readFileToString(filepath1);
        String file2 = readFileToString(filepath2);

        Map<String, Object> map1 = Parser.parserToMap(file1, filepath1);
        Map<String, Object> map2 = Parser.parserToMap(file2, filepath2);

        Map<String, DiffProperty> diff = TreeDiffer.compareData(map1, map2);

        return Formatter.formatter(format, diff);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}

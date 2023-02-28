package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
//import static hexlet.code.FileUtils.readFileToString;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String file1 = readFileToString(filepath1);
        String file2 = readFileToString(filepath2);

        String fileExtension1 = getFileExtension(filepath1);
        String fileExtension2 = getFileExtension(filepath2);

        Map<String, Object> map1 = Parser.parserToMap(file1, fileExtension1);
        Map<String, Object> map2 = Parser.parserToMap(file2, fileExtension2);

        List<DiffProperty> diff = TreeDiffer.compareData(map1, map2);

        return Formatter.formatter(format, diff);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String readFileToString(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getFileExtension(String filepath) {
        String fullPath =  Paths.get(filepath).toAbsolutePath().normalize().toString();
        int index = fullPath.indexOf('.');
        return index == -1 ? null : fullPath.substring(index + 1).toUpperCase();
    }
}

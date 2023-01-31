package hexlet.code;

import java.util.Map;

import static hexlet.code.Utility.getType;
import static hexlet.code.Utility.readFileToString;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String file1 = readFileToString(filepath1);
        String file2 = readFileToString(filepath2);

        String typeFile = getType(filepath1, filepath2);
        Map<String, Property> diff = Parser.compareData(file1, file2, typeFile);

        if (format.equals("stylish")) {
            return Stylish.writeCompare(diff);
        }

        return Stylish.writeCompare(diff);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}

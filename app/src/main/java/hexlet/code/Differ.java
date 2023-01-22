package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    private static Map<String, Object> mapFirstFile;
    private static Map<String, Object> mapSecondFile;
    private List<String> sortedKeys;

    private static Map<String, Object> readFileToMap(final String filepath) throws IOException {
        Path path = Paths.get(filepath);
        String str = Files.readString(path);
        if (str.equals("")) {
            return new HashMap<String, Object>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(str, new TypeReference<Map<String, Object>>() { });
    }

    private static String generateReport(List<String> keys) {
        var result = new StringBuilder("{\n");
        for (String key: keys) {
            if (!mapFirstFile.containsKey(key)) {
                result.append("  + " + key + ": " + mapSecondFile.get(key).toString() + "\n");
            } else if (!mapSecondFile.containsKey(key)) {
                result.append("  - " + key + ": " + mapFirstFile.get(key).toString() + "\n");
            } else if (mapFirstFile.get(key).equals(mapSecondFile.get(key))) {
                result.append("    " + key + ": " + mapFirstFile.get(key).toString() + "\n");
            } else {
                result.append("  - " + key + ": " + mapFirstFile.get(key).toString() + "\n")
                        .append("  + " + key + ": " + mapSecondFile.get(key).toString() + "\n");
            }
        }
        result.append("}");
        return result.toString();
    }


    public static String generate(String filepath1, String filepath2) throws IOException {
        mapFirstFile = readFileToMap(filepath1);
        mapSecondFile = readFileToMap(filepath2);
        List<String> sortedKeys = new ArrayList<>(mapFirstFile.keySet());
        for (String key: mapSecondFile.keySet()) {
            if (!sortedKeys.contains(key)) {
                sortedKeys.add(key);
            }
        }
        Collections.sort(sortedKeys);
        return generateReport(sortedKeys);
    }
}

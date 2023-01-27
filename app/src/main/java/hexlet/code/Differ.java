package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {


    private static Map<String, Object> readFileToMap(final String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String str = Files.readString(path);
        if (str.equals("")) {
            return new HashMap<String, Object>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(str, new TypeReference<Map<String, Object>>() { });
    }




    public static String generate(String filepath1, String filepath2) throws IOException {
        App.setMapFirstFile(readFileToMap(filepath1));
        App.setMapSecondFile(readFileToMap(filepath2));
        App.takeSortedKey();
        return App.generateReport(App.getSortedKeys());
    }
}

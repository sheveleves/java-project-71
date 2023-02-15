package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parserToMap(String data, String pathFile) throws Exception {
        if (data.isEmpty()) {
            return new HashMap<>();
        }
        String fullPath = FileUtils.getFullPath(pathFile);

        int index = fullPath.indexOf('.');
        String typeFile = index == -1 ? null : fullPath.substring(index + 1).toUpperCase();
        if (typeFile.equals("YML") || typeFile.equals("YAML")) {
            typeFile = "YAML";
        }

        ObjectMapper mapper;
        switch (typeFile) {
            case "JSON":
                mapper = new ObjectMapper();
                break;
            case "YAML":
                mapper = new YAMLMapper();
                break;
            default:
                throw new Exception("File type not defined!");
        }
        return mapper.readValue(data, new TypeReference<Map<String, Object>>() { });
    }
}

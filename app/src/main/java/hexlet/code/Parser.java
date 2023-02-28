package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parserToMap(String data, String fileExtension) throws Exception {

        if (data.isEmpty()) {
            return new HashMap<>();
        }

        switch (fileExtension) {
            case "JSON":
                return parseJson(data);
            case "YAML", "YML":
                return parseJaml(data);
            default:
                throw new RuntimeException ("File type not defined!");
        }
    }

    private static Map<String, Object> parseJson(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, new TypeReference<>() { });
    }

    private static Map<String, Object> parseJaml(String data) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(data, new TypeReference<>() { });
    }
}

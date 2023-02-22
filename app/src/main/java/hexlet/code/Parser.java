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

        ObjectMapper mapper;

        switch (fileExtension) {
            case "JSON":
                mapper = new ObjectMapper();
                break;
            case "YAML", "YML":
                mapper = new YAMLMapper();
                break;
            default:
                throw new Exception("File type not defined!");
        }

        return mapper.readValue(data, new TypeReference<Map<String, Object>>() { });
    }
}

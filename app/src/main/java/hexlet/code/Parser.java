package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Parser {

    public static Map<String, Property> compareData(String data1,
                                                  String data2, String typeFiles) throws Exception {

        Map<String, Object> map1 = getMapFromData(data1, typeFiles);
        Map<String, Object> map2 = getMapFromData(data2, typeFiles);
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());


        Map<String, Property> compareMap = new TreeMap<>();
        for (String key: keys) {
            if (!map1.containsKey(key)) {
                compareMap.put(key, new Property(Property.ADD, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                compareMap.put(key, new Property(Property.DELETE, map1.get(key)));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                compareMap.put(key, new Property(Property.UNCHANGED, map1.get(key), map2.get(key)));
            } else {
                compareMap.put(key, new Property(Property.CHANGED, map1.get(key), map2.get(key)));
            }
        }
        return compareMap;
    }



    private static Map<String, Object> getMapFromData(final String data, final String typeFile) throws Exception {
        if (data.isEmpty()) {
            return new LinkedHashMap<>();
        }

        ObjectMapper mapper;
        switch (typeFile) {
            case "JSON":
                mapper = new ObjectMapper();
                break;
            case "YAML":
                mapper = new YAMLMapper();
                break;
            default: throw new Exception("File type not defined!");
        }
        return mapper.readValue(data, new TypeReference<Map<String, Object>>() { });
    }

}

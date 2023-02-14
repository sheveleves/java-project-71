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

    public static Map<String, DiffProperty> compareData(String data1,
                                                        String data2, String pathFiles) throws Exception {

        Map<String, Object> map1 = getMapFromData(data1, pathFiles);
        Map<String, Object> map2 = getMapFromData(data2, pathFiles);
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());


        Map<String, DiffProperty> compareMap = new TreeMap<>();
        for (String key: keys) {
            if (!map1.containsKey(key)) {
                compareMap.put(key, new DiffProperty(DiffProperty.ADD, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                compareMap.put(key, new DiffProperty(DiffProperty.DELETE, map1.get(key)));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                compareMap.put(key, new DiffProperty(DiffProperty.UNCHANGED, map1.get(key), map2.get(key)));
            } else {
                compareMap.put(key, new DiffProperty(DiffProperty.CHANGED, map1.get(key), map2.get(key)));
            }
        }
        return compareMap;
    }



    private static Map<String, Object> getMapFromData(final String data, final String pathFile) throws Exception {
        if (data.isEmpty()) {
            return new LinkedHashMap<>();
        }

        int index = pathFile.indexOf('.');
        String typeFile = index == -1 ? null : pathFile.substring(index + 1).toUpperCase();
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
            default: throw new Exception("File type not defined!");
        }
        return mapper.readValue(data, new TypeReference<Map<String, Object>>() { });
    }

}

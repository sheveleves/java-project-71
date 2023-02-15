package hexlet.code;

import hexlet.code.DiffProperty.Property;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;

public class TreeDiffer {
    public static Map<String, DiffProperty> compareData(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        Map<String, DiffProperty> compareMap = new TreeMap<>();
        for (String key: keys) {
            if (!map1.containsKey(key)) {
                compareMap.put(key, new DiffProperty(Property.ADD, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                compareMap.put(key, new DiffProperty(Property.DELETE, map1.get(key)));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                compareMap.put(key, new DiffProperty(Property.UNCHANGED, map1.get(key), map2.get(key)));
            } else {
                compareMap.put(key, new DiffProperty(Property.CHANGED, map1.get(key), map2.get(key)));
            }
        }
        return compareMap;
    }
}

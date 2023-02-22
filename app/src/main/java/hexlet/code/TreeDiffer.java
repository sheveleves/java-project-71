package hexlet.code;

import hexlet.code.DiffProperty.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TreeDiffer {
    public static List<DiffProperty> compareData(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        List<DiffProperty> compareList = new ArrayList<>();
        for (String key: keys) {
            if (!map1.containsKey(key)) {
                compareList.add(new DiffProperty(key, Property.ADD, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                compareList.add(new DiffProperty(key, Property.DELETE, map1.get(key), map2.get(key)));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                compareList.add(new DiffProperty(key, Property.UNCHANGED, map1.get(key), map2.get(key)));
            } else {
                compareList.add(new DiffProperty(key, Property.CHANGED, map1.get(key), map2.get(key)));
            }
        }
        return compareList;
    }
}

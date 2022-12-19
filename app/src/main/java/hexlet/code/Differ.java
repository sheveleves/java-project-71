package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

	public static Map<String, Object> readFileToMap(final String filepath) throws IOException{
		Path path = Paths.get(filepath);
		String str = Files.readString(path);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(str, new TypeReference<Map<String, Object>>(){});
	}

	public static String generate(String filepath1, String filepath2) throws IOException{
		Map<String, Object> mapFirstFile = readFileToMap(filepath1);
		Map<String, Object> mapSecondFile = readFileToMap(filepath2);
		List<String> sortedKeys = new ArrayList<>(mapFirstFile.keySet());
		for (String key: mapSecondFile.keySet()) {
			if (!sortedKeys.contains(key)) {
				sortedKeys.add(key);
			}
		}
		Collections.sort(sortedKeys);
		var result = new StringBuilder("{\n");
		for (String key: sortedKeys) {
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

		System.out.println(result.toString());

		return result.toString();
	}
}

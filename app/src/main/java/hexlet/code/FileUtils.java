package hexlet.code;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileUtils {
    public static String readFileToString(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static String getTypeFile(String filepath) {
        String fullPath = Paths.get(filepath).toAbsolutePath().normalize().toString();
        int index = fullPath.indexOf('.');
        String typeFile = index == -1 ? null : fullPath.substring(index + 1);
        if (typeFile.toUpperCase().equals("YML") || typeFile.toUpperCase().equals("YAML")) {
            return "YAML";
        } else {
            return "JSON";
        }
    }

    public static String getType(String filepath1, String filepath2) throws Exception {
        String typeFile1 = getTypeFile(filepath1);
        String typeFile2 = getTypeFile(filepath2);

        if (typeFile1.equals(typeFile2)) {
            return typeFile1;
        } else {
            throw new Exception("Files must have same type!");
        }
    }




}

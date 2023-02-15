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

    public static String getFullPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize().toString();
    }
}

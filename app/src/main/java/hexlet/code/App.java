package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    private static Map<String, Object> mapFirstFile;
    private static Map<String, Object> mapSecondFile;
    private static List<String> sortedKeys = new ArrayList<>();


    public static void setMapFirstFile(Map<String, Object> mapFirstFile) {
        App.mapFirstFile = mapFirstFile;
    }

    public static void setMapSecondFile(Map<String, Object> mapSecondFile) {
        App.mapSecondFile = mapSecondFile;
    }

    public static Map<String, Object> getMapFirstFile() {
        return mapFirstFile;
    }

    public static Map<String, Object> getMapSecondFile() {
        return mapSecondFile;
    }

    public static List<String> getSortedKeys() {
        return sortedKeys;
    }

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
//    for debug
//    @Parameters(index = "0", description = "path to first file",
//            defaultValue = "/home/shev/MyProject2/java-project-71/app/src/test/resources/file5.yml")
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

//    for debug
//    @Parameters(index = "1", description = "path to second file",
//            defaultValue = "/home/shev/MyProject2/java-project-71/app/src/test/resources/file6.yml")
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


    @Override
    public final String call() throws Exception {
        System.out.println(Parser.generate(filepath1, filepath2));
        return new String();
    }
    public static String generateReport(List<String> keys) {
        var result = new StringBuilder("{\n");
        for (String key: keys) {
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
        return result.toString();
    }

    public static void takeSortedKey() {
        sortedKeys.clear();
        sortedKeys.addAll(mapFirstFile.keySet());
        for (String key : mapSecondFile.keySet()) {
            if (!sortedKeys.contains(key)) {
                sortedKeys.add(key);
            }
        }
        Collections.sort(sortedKeys);
    }

}

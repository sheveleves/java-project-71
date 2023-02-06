package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;
//    for debug
//    @Parameters(index = "0", description = "path to first file",
//            defaultValue = "/home/shev/MyProject2/java-project-71/app/src/test/resources/file3.yml")
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

//    for debug
//    @Parameters(index = "1", description = "path to second file",
//            defaultValue = "/home/shev/MyProject2/java-project-71/app/src/test/resources/file4.yml")
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public final Integer call() throws Exception {
        String result = Differ.generate(filepath1, filepath2, format);
        System.out.println(result);
        return 0;
    }
}

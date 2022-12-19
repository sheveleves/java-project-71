package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
		mixinStandardHelpOptions = true,
		description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

	@Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
	String format;
//	for debug
//	@Parameters(index = "0", description = "path to first file", defaultValue = "./file1.json")
	@Parameters(index = "0", description = "path to first file")
	private String filepath1;

//	for debug
//	@Parameters(index = "1", description = "path to second file", defaultValue = "./file2.json")
	@Parameters(index = "1", description = "path to second file")
	private String filepath2;

	public static void main(String[] args) throws Exception {
		System.out.println();
		int exitCode = new CommandLine(new App()).execute(args);
		System.exit(exitCode);
	}


	@Override
	public String call() throws Exception {
		Differ.generate(filepath1, filepath2);
		return new String();
	}
}

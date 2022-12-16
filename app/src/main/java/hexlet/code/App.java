package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;

@Command(name = "gendiff",
		mixinStandardHelpOptions = true,
		description = "Compares two configuration files and shows a difference.")
public class App implements Runnable{

	@Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
	String format;
	@Parameters(index = "0", description = "path to first file")
	private String filepath1;
	@Parameters(index = "1", description = "path to first file")
	private String filepath2;

	public static void main(String[] args) {
		System.out.println();
		int exitCode = new CommandLine(new App()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
		System.out.println("Hello World!");
	}
}

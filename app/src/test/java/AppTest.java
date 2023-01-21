import hexlet.code.Differ;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {

    private String filePath1 = "src/test/resources/file1.json";
    private String filePath2 = "src/test/resources/file2.json";
    private String emptyFile = "src/test/resources/emptyFile.json";

    @Test
    public void compareJson1() throws IOException {
        String actual = Differ.generate(filePath1, filePath2);
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void compareJson2() throws IOException {
        String actual = Differ.generate(filePath2, filePath1);
        String expected = "{\n"
                + "  + follow: false\n"
                + "    host: hexlet.io\n"
                + "  + proxy: 123.234.53.22\n"
                + "  - timeout: 20\n"
                + "  + timeout: 50\n"
                + "  - verbose: true\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void emptyJson1() throws IOException {
        String actual = Differ.generate(filePath1, emptyFile);
        String expected = "{\n"
                + "  - follow: false\n"
                + "  - host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void emptyJson2() throws IOException {
        String actual = Differ.generate(emptyFile, filePath2);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
}

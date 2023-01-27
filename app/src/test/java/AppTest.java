import hexlet.code.Differ;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {

    private String file1Json = "src/test/resources/file1.json";
    private String file2Json = "src/test/resources/file2.json";
    private String emptyJson = "src/test/resources/emptyJson.json";

    private String file3Yml = "src/test/resources/file3.yml";
    private String file4Yml = "src/test/resources/file4.yml";
    private String file5Yml = "src/test/resources/file5.yml";
    private String file6Yml = "src/test/resources/file6.yml";

    @Test
    public void compareJson1() throws IOException {
        String actual = Differ.generate(file1Json, file2Json);
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
        String actual = Differ.generate(file2Json, file1Json);
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
    public void compareYml() throws IOException {
        String actual = Parser.generate(file5Yml, file6Yml);
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
    public void emptyJson1() throws IOException {
        String actual = Differ.generate(file1Json, emptyJson);
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
        String actual = Differ.generate(emptyJson, file2Json);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void compareYml1() throws IOException {
        String actual = Parser.generate(file3Yml, file4Yml);
        String expected = "{\n"
                + "    author: Evgeniy Sheveles\n"
                + "  - data: [data17, data22, data20]\n"
                + "  + data: [data17, data5, data20]\n"
                + "  - description: Report time at 09.00 26/01/23\n"
                + "  + description: Report time at 10.00 26/01/23\n"
                + "    host: hexlet.oi\n"
                + "  - ip: [80.80.80.80, 192.168.20.30, 10.10.10.5]\n"
                + "  + ip: [80.80.80.80, 192.168.20.30]\n"
                + "  - setting-publication: 2020\n"
                + "  + setting-publication: 2023\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void compareYml2() throws IOException {
        String actual = Parser.generate(file4Yml, file3Yml);
        String expected = "{\n"
                + "    author: Evgeniy Sheveles\n"
                + "  - data: [data17, data5, data20]\n"
                + "  + data: [data17, data22, data20]\n"
                + "  - description: Report time at 10.00 26/01/23\n"
                + "  + description: Report time at 09.00 26/01/23\n"
                + "    host: hexlet.oi\n"
                + "  - ip: [80.80.80.80, 192.168.20.30]\n"
                + "  + ip: [80.80.80.80, 192.168.20.30, 10.10.10.5]\n"
                + "  - setting-publication: 2023\n"
                + "  + setting-publication: 2020\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
}

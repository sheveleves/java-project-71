import hexlet.code.Differ;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;


public class AppTest {

    private String file1Json = "src/test/resources/file1.json";
    private String file2Json = "src/test/resources/file2.json";
    private String emptyJson = "src/test/resources/emptyJson.json";

    private String file3Yml = "src/test/resources/file3.yml";
    private String file4Yml = "src/test/resources/file4.yml";
    private String file5Yml = "src/test/resources/file5.yml";
    private String file6Yml = "src/test/resources/file6.yml";
    private String internal3Json = "src/test/resources/internal3.json";
    private String internal4Json = "src/test/resources/internal4.json";
    private String testJson = "src/test/resources/expectedTestJson.txt";

    @Test
    public void testJson() throws Exception {
        String actual = Differ.generate(internal3Json, internal4Json, "json");
        String expected = Files.readString(Path.of(testJson));
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testPlain() throws Exception {
        String actual = Differ.generate(internal3Json, internal4Json, "plain");
        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void compareJson1() throws Exception {
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
    public void compareJson2() throws Exception {
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
    public void compareYml() throws Exception {
        String actual = Differ.generate(file5Yml, file6Yml);
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
    public void emptyJson1() throws Exception {
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
    public void emptyJson2() throws Exception {
        String actual = Differ.generate(emptyJson, file2Json);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void compareYml1() throws Exception {
        String actual = Differ.generate(file3Yml, file4Yml);
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void compareInternalJson() throws Exception {
        String actual = Differ.generate(internal3Json, internal4Json, "stylish");
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        assertThat(actual).isEqualTo(expected);
    }
}

package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.DiffProperty;

import java.util.List;

public class Formatter {
    public static String formatter(String format, List<DiffProperty> diff) throws JsonProcessingException {

        switch (format) {
            case "plain":
                return Plain.writeCompare(diff);
            case "json":
                return Json.writeCompare(diff);
            default:
                return Stylish.writeCompare(diff);
        }
    }
}

package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Property;

import java.util.Map;

public class Formatter {
    public static String formatter(String format, Map<String, Property> diff) throws JsonProcessingException {
        if (format.equals("plain")) {
            return Plain.writeCompare(diff);
        } else if (format.equals("json")) {
            return Json.writeCompare(diff);
        } else {
            return Stylish.writeCompare(diff);
        }
    }
}

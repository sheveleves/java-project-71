package hexlet.code.formatters;

import hexlet.code.Property;

import java.util.Map;

public class Formatter {
    public static String formatter(String format, Map<String, Property> diff) {
        if (format.equals("plain")) {
            return Plain.writeCompare(diff);
        } else {
            return Stylish.writeCompare(diff);
        }
    }
}

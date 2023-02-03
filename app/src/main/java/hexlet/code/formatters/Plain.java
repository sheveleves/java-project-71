package hexlet.code.formatters;

import hexlet.code.Property;

import java.util.Map;

public class Plain {
    public static String writeCompare(Map<String, Property> diff) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Property> entry : diff.entrySet()) {
            switch (entry.getValue().getState()) {
                case Property.ADD -> builder.append("Property '")
                        .append(entry.getKey()).append("' was added with value: ")
                        .append(printUpdate(entry.getValue())).append("\n");
                case Property.DELETE -> builder.append("Property '")
                        .append(entry.getKey()).append("' was removed").append("\n");
                case Property.CHANGED -> builder.append("Property '")
                        .append(entry.getKey()).append("' was updated. ")
                        .append(printUpdate(entry.getValue())).append("\n");
                default -> { }
            }
        }
        return builder.toString();
    }

    public static String printUpdate(Property object) {

        if (object.getState().equals(Property.CHANGED)) {
            return String.format("From %s to %s", printForCheckPrimitive(object.getOldValue()),
                    printForCheckPrimitive(object.getCurrentValue()));
        }
        if (object.getState().equals(Property.ADD)) {
            return String.format("%s", printForCheckPrimitive(object.getCurrentValue()));
        }
        return "";
    }

    public static String printForCheckPrimitive(Object object) {

        if (object == null) {
            return "null";
        }

        if ((object instanceof Integer) || (object instanceof Boolean)) {
            return object.toString();
        } else if (object instanceof String) {
            return "'" + object.toString() + "'";
        } else {
            return "[complex value]";
        }
    }
}

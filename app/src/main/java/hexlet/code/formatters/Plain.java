package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.Map;

public class Plain {
    public static String writeCompare(Map<String, DiffProperty> diff) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, DiffProperty> entry : diff.entrySet()) {
            switch (entry.getValue().getState()) {
                case DiffProperty.ADD -> builder.append("Property '")
                        .append(entry.getKey()).append("' was added with value: ")
                        .append(printUpdate(entry.getValue())).append("\n");
                case DiffProperty.DELETE -> builder.append("Property '")
                        .append(entry.getKey()).append("' was removed").append("\n");
                case DiffProperty.CHANGED -> builder.append("Property '")
                        .append(entry.getKey()).append("' was updated. ")
                        .append(printUpdate(entry.getValue())).append("\n");
                default -> { }
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static String printUpdate(DiffProperty object) {

        if (object.getState().equals(DiffProperty.CHANGED)) {
            return String.format("From %s to %s", printForCheckPrimitive(object.getOldValue()),
                    printForCheckPrimitive(object.getCurrentValue()));
        }
        if (object.getState().equals(DiffProperty.ADD)) {
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

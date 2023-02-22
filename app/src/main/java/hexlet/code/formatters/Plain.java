package hexlet.code.formatters;

import hexlet.code.DiffProperty;
import hexlet.code.DiffProperty.Property;

import java.util.List;

public class Plain {
    public static String writeCompare(List<DiffProperty> diff) {
        StringBuilder builder = new StringBuilder();

        for (DiffProperty item : diff) {
            switch (item.getState()) {
                case ADD -> builder.append("Property '")
                        .append(item.getField()).append("' was added with value: ")
                        .append(printUpdate(item)).append("\n");
                case DELETE -> builder.append("Property '")
                        .append(item.getField()).append("' was removed").append("\n");
                case CHANGED -> builder.append("Property '")
                        .append(item.getField()).append("' was updated. ")
                        .append(printUpdate(item)).append("\n");
                default -> { }
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static String printUpdate(DiffProperty item) {

        if (item.getState().equals(Property.CHANGED)) {
            return String.format("From %s to %s", printForCheckPrimitive(item.getOldValue()),
                    printForCheckPrimitive(item.getCurrentValue()));
        }
        if (item.getState().equals(Property.ADD)) {
            return String.format("%s", printForCheckPrimitive(item.getCurrentValue()));
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

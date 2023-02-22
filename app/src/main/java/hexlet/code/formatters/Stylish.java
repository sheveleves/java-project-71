package hexlet.code.formatters;

import hexlet.code.DiffProperty;
import hexlet.code.DiffProperty.Property;
import java.util.List;

public class Stylish {

    public static String writeCompare(List<DiffProperty> diff) {
        StringBuilder builder = new StringBuilder();
        String add = "  + ";
        String delete = "  - ";
        String align = "    ";

        if (diff.isEmpty()) {
            return "{}";
        }

        builder.append("{");
        for (DiffProperty item : diff) {
            if (item.getState().equals(Property.ADD)) {
                builder.append("\n")
                        .append(add)
                        .append(item.getField()).append(": ").append(item.getCurrentValue());
            } else if (item.getState().equals(Property.DELETE)) {
                builder.append("\n")
                        .append(delete)
                        .append(item.getField()).append(": ").append(item.getOldValue());
            } else if (item.getState().equals(Property.UNCHANGED)) {
                builder.append("\n")
                        .append(align)
                        .append(item.getField()).append(": ").append(item.getCurrentValue());
            } else if (item.getState().equals(Property.CHANGED)) {
                builder.append("\n")
                        .append(delete)
                        .append(item.getField()).append(": ").append(item.getOldValue());
                builder.append("\n")
                        .append(add)
                        .append(item.getField()).append(": ").append(item.getCurrentValue());
            }
        }
        builder.append("\n}");

        return builder.toString();
    }
}

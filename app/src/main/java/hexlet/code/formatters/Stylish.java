package hexlet.code.formatters;

import hexlet.code.DiffProperty;

import java.util.Map;

public class Stylish {
    public static String writeCompare(Map<String, DiffProperty> diff) {
        StringBuilder builder = new StringBuilder();
        String add = "  + ";
        String delete = "  - ";
        String align = "    ";

        if (diff.isEmpty()) {
            return "{}";
        }

        builder.append("{");
        for (Map.Entry<String, DiffProperty> entry : diff.entrySet()) {
            if (entry.getValue().getState().equals(DiffProperty.ADD)) {
                builder.append("\n")
                        .append(add)
                        .append(entry.getKey()).append(": ").append(entry.getValue().getCurrentValue());
            } else if (entry.getValue().getState().equals(DiffProperty.DELETE)) {
                builder.append("\n")
                        .append(delete)
                        .append(entry.getKey()).append(": ").append(entry.getValue().getCurrentValue());
            } else if (entry.getValue().getState().equals(DiffProperty.UNCHANGED)) {
                builder.append("\n")
                        .append(align)
                        .append(entry.getKey()).append(": ").append(entry.getValue().getCurrentValue());
            } else if (entry.getValue().getState().equals(DiffProperty.CHANGED)) {
                builder.append("\n")
                        .append(delete)
                        .append(entry.getKey()).append(": ").append(entry.getValue().getOldValue());
                builder.append("\n")
                        .append(add)
                        .append(entry.getKey()).append(": ").append(entry.getValue().getCurrentValue());
            }
        }
        builder.append("\n}");

        return builder.toString();
    }
}

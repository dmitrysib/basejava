package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.List;

public class ListSection extends AbstractSection<String> {
    @Serial
    private static final long serialVersionUID = 1L;

    public ListSection(List<String> elements) {
        super(elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String element : elements) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("\t'").append(element).append('\'');
        }
        return "[\n" + sb.toString() + "\n]";
    }
}
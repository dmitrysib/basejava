package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.List;

public class Organization extends AbstractSection<Experience> {
    @Serial
    private static final long serialVersionUID = 1L;

    public Organization() {
    }

    public Organization(List<Experience> elements) {
        super(elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Experience experience : elements) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(experience);
        }
        return '[' + sb.toString() + ']';
    }
}
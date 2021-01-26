package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization extends AbstractSection<Experience> {

    public Organization(List<Experience> elements) {
        super(elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Experience experience : elements) {
            if (sb.length() > 0) {
                sb.append(",\n");
            }
            sb.append("\t").append(experience);
        }
        return "[\n" + sb.toString() + "\n]";
    }
}
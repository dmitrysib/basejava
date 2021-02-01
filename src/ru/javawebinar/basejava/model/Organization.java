package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Experience> elements = new ArrayList<>();

    public Organization() {
    }

    public Organization(List<Experience> elements) {
        Objects.requireNonNull(elements, "elements cannot be null");
        this.elements = elements;
    }

    public List<Experience> getElements() {
        return elements;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
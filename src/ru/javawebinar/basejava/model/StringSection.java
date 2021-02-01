package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.Objects;

public class StringSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private String value;

    public StringSection() {
    }

    public StringSection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return '\'' + value + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

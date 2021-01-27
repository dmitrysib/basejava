package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSection<ST> {
    protected final List<ST> elements;

    public AbstractSection(List<ST> elements) {
        Objects.requireNonNull(elements, "elements cannot be null");
        this.elements = new ArrayList<>(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSection<?> that = (AbstractSection<?>) o;
        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
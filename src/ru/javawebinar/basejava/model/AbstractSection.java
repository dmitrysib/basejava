package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSection<ST> {
    protected List<ST> elements;

    public AbstractSection(List<ST> elements) {
        Objects.requireNonNull(elements, "elements cannot be null");
        this.elements = new ArrayList<>(elements);
    }
}
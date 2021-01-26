package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringSection extends AbstractSection<String> {

    public StringSection(String element) {
        Objects.requireNonNull(element, "element cannot be null");
        super.add(element);
    }
}

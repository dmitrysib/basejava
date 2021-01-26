package ru.javawebinar.basejava.model;

import java.util.Collections;

public class StringSection extends AbstractSection<String> {

    public StringSection(String element) {
        super(Collections.singletonList(element));
    }

    @Override
    public String toString() {
        return elements.get(0);
    }
}

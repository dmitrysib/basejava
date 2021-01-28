package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.Collections;

public class StringSection extends AbstractSection<String> {
    @Serial
    private static final long serialVersionUID = 1L;

    public StringSection(String element) {
        super(Collections.singletonList(element));
    }

    @Override
    public String toString() {
        return elements.get(0);
    }
}

package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSection<ST> {
    protected List<ST> elements = new ArrayList<>();

    public void add(ST element) {
        elements.add(element);
    }

    public void set(ST element) {
        if (elements.size() > 0) {
            elements.set(elements.size() - 1, element);
        } else {
            elements.add(element);
        }
    }

    public void set(int index, ST element) {
        elements.set(index, element);
    }

    public void del(int index) {
        elements.remove(index);
    }

    public ST get() {
        return elements.get(elements.size() - 1);
    }

    public ST get(int index) {
        return elements.get(index);
    }
}
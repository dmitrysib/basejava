package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSection<ST> implements Section {
    protected List<ST> elements = new ArrayList<>();

    public void add(ST element) {
        elements.add(element);
    }

    public void set(int index, ST element) {
        elements.set(index, element);
    }

    public void del(int index) {
        elements.remove(index);
    }

    public ST get(int index) {
        return elements.get(index);
    }
}
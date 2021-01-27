package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Experience implements Serializable {
    private final Link homePage;
    private final List<Position> positions;

    public Experience(String name, String url, Position... positions) {
        homePage = new Link(name, url);
        this.positions = new ArrayList<>(Arrays.asList(positions));
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\t" + homePage + "\n" +
                "\t\t" + positions + "\n" +
                "\t}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return homePage.equals(that.homePage) && positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }
}
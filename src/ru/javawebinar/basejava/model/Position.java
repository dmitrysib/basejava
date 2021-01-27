package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Position implements Serializable {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Position(String title, LocalDate startDate, LocalDate endDate) {
        this(title, startDate, endDate, "");
    }

    public Position(String title, LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(startDate, "start date cannot be null");
        Objects.requireNonNull(endDate, "end date cannot be null");

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return title.equals(that.title) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "\n\t\t\t{\n" +
                "\t\t\t\t" + title + "\n" +
                "\t\t\t\t" + startDate + "\n" +
                "\t\t\t\t" + endDate + "\n" +
                "\t\t\t\t" + description + "\n" +
                "\t\t\t}\n\t\t";
    }
}

package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Experience implements Serializable {
    private final Link homePage;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Experience(String name, String url, String title, LocalDate startDate,
                      LocalDate endDate) {
        this(name, url, title, startDate, endDate, "");
    }

    public Experience(String name, String url, String title, LocalDate startDate,
                      LocalDate endDate, String description) {
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(startDate, "position cannot be null");
        Objects.requireNonNull(endDate, "position cannot be null");

        this.homePage = Link.of(name, url);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\t" + homePage + "\n" +
                "\t\t" + title + "\n" +
                "\t\t" + startDate + "\n" +
                "\t\t" + endDate + "\n" +
                "\t\t" + description + "\n" +
                "\t}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return homePage.equals(that.homePage) && title.equals(that.title) && startDate.equals(that.startDate)
                && endDate.equals(that.endDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, title, startDate, endDate, description);
    }
}
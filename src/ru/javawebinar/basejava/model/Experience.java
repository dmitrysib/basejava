package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final Link link;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Experience(Link link, String title, LocalDate startDate,
                      LocalDate endDate) {
        this(link, title, startDate, endDate, "");
    }

    public Experience(Link link, String title, LocalDate startDate,
                      LocalDate endDate, String description) {
        Objects.requireNonNull(link, "link cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(startDate, "position cannot be null");
        Objects.requireNonNull(endDate, "position cannot be null");

        this.link = link;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\t" + link + "\n" +
                "\t\t" + title + "\n" +
                "\t\t" + startDate + "\n" +
                "\t\t" + endDate + "\n" +
                "\t\t" + description + "\n" +
                "\t}";
    }
}
package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final String name;
    private final String url;
    private final String position;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Experience(String name, String url, String position, LocalDate startDate,
                      LocalDate endDate) {
        this(name, url, position, startDate, endDate, "");
    }

    public Experience(String name, String url, String position, LocalDate startDate,
                      LocalDate endDate, String description) {
        Objects.requireNonNull(position, "position cannot be null");
        Objects.requireNonNull(startDate, "position cannot be null");
        Objects.requireNonNull(endDate, "position cannot be null");

        this.name = name;
        this.url = url;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\t" + name + "\n" +
                "\t\t" + url + "\n" +
                "\t\t" + position + "\n" +
                "\t\t" + startDate + "\n" +
                "\t\t" + endDate + "\n" +
                "\t\t" + description + "\n" +
                "\t}";
    }
}
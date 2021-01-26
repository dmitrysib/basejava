package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class ComplexElement {
    private final String name;
    private final String url;
    private final String position;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public ComplexElement(String name, String url, String position, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.url = url;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}

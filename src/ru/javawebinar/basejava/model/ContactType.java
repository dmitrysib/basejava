package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("PHONE"),
    EMAIL("EMAIL"),
    SKYPE("SKYPE"),
    WEBPAGE("WEBPAGE"),
    GITHUB("GITHUB"),
    LINKEDIN("LINKEDIN");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

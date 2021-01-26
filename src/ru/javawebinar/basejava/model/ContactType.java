package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    EMAIL("Почта"),
    SKYPE("Skype"),
    GITHUB("Профиль GitHub"),
    LINKEDIN("Профиль LinkedIn"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    WEBPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

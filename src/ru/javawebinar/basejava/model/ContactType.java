package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    EMAIL("Почта"){
        @Override
        public String toHtml0(String value) {
            return "<a href=\"mailto:" + value + "\">" + value + "</a>";
        }
    },
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return "<a href=\"skype:" + value + "\">" + value + "</a>";
        }
    },
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

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return value == null ? "" : toHtml0(value);
    }
}

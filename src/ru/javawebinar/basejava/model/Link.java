package ru.javawebinar.basejava.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Link {
    private final String url;
    private final String title;

    public Link(String url, String title) {
        Objects.requireNonNull(url, "url cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        this.url = url;
        this.title = title;
    }

    public Link(Link o) {
        url = o.url;
        title = o.title;
    }

    @Override
    public String toString() {
        return "[URL " + url + "]" + title + "[/URL]";
    }
}

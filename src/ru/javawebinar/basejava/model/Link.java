package ru.javawebinar.basejava.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Link {
    private final String url;
    private final String title;

    public Link(String title, String url) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(url, link.url) && title.equals(link.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title);
    }
}

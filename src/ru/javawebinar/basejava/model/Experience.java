package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Experience implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Link homePage;
    private final List<Position> positions;

    public Experience(String name, String url, Position... positions) {
        homePage = new Link(name, url);
        this.positions = new ArrayList<>(Arrays.asList(positions));
    }

    @Override
    public String toString() {
        return "Experience {" + homePage + ", " + positions + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return homePage.equals(that.homePage) && positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    public static class Position implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

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
            return "Position {'" + title + "', '" + startDate + "', '" + endDate + "' ,'" + description + "'}";
        }
    }

    public static class Link implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private final String url;
        private final String title;

        public Link(String title, String url) {
            Objects.requireNonNull(title, "title cannot be null");
            this.url = url;
            this.title = title;
        }

        @Override
        public String toString() {
            return "Link {'" + url + "', '" + title + "'}";
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
}
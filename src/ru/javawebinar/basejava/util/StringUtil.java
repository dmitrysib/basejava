package ru.javawebinar.basejava.util;

public class StringUtil {
    public static void requireNonEmpty(String value, String message) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}

package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate of(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String format(LocalDate date) {
        return date.isEqual(NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
    }
}

package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
    private static final String nowString = "Сейчас";

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static LocalDate of(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String format(LocalDate date) {
        return date.isEqual(NOW) ? nowString : date.format(DATE_FORMATTER);
    }

    public static LocalDate parse(String value) {
        if (value.equals(nowString)) return NOW;
        else {
            YearMonth ym = YearMonth.parse(value, DATE_FORMATTER);
            return ym.atDay(1);
        }
    }
}

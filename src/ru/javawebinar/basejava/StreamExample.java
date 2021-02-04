package ru.javawebinar.basejava;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        System.out.println(list + " --> oddOrEven --> " + oddOrEven(list));

        int[] array = {1, 2, 3, 3, 2, 3};
        System.out.println(Arrays.toString(array) + " -->  minValue --> " + minValue(array));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

        var l = integers.parallelStream().collect(Collectors.groupingBy(i -> i % 2 == 0 ? "even" : "odd"));
        System.out.println(l);

        return null;
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }
}

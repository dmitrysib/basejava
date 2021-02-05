package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> list = List.of(8, 9);
        System.out.println(list + " --> oddOrEven --> " + oddOrEven(list));

        int[] array = {1, 2, 3, 3, 2, 3};
        System.out.println(Arrays.toString(array) + " -->  minValue --> " + minValue(array));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        var result = integers
                .stream()
                .collect(Collectors.groupingBy(i -> i % 2 == 0));
        return result.get(result.get(false).size() % 2 == 0);
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }
}

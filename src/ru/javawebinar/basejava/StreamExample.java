package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        System.out.println(list + " --> oddOrEven --> " + oddOrEven(list));

        int[] array = {1, 2, 3, 3, 2, 3};
        System.out.println(Arrays.toString(array) + " -->  minValue --> " + minValue(array));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();

        int sum = integers.stream()
                .peek(i -> {
                    if (i % 2 == 0) {
                        even.add(i);
                    } else {
                        odd.add(i);
                    }
                })
                .reduce(Integer::sum).orElse(0);

        return sum % 2 == 0 ? even : odd;
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> a * 10 + b);
    }
}

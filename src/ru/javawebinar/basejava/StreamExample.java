package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println(list + " --> oddOrEven --> " + oddOrEven(list));

        int[] array = {1, 2, 3, 3, 2, 3};
        System.out.println(Arrays.toString(array) + " -->  minValue --> " + minValue(array));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

//        var odd = new ArrayList<>(integers);
//        var even = new ArrayList<>(integers);

//        int sum = integers.stream()
//                .peek(i -> {
//                    if (i % 2 == 0) {
//                        odd.remove(i);
//                    } else {
//                        even.remove(i);
//                    }
//                })
//                .reduce(Integer::sum).orElse(0);

        int sum = integers.stream().reduce(Integer::sum).orElse(0);
        return integers.stream().filter(i -> i % 2 != sum % 2).collect(Collectors.toList());
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }
}

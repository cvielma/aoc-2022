// (C) king.com Ltd 2022

package org.example;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3Part1 {


    private static final AtomicInteger PRIORITY = new AtomicInteger(0);
    private static final Map<String, Integer> PRIORITY_MAP =
            Arrays.stream("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""))
                    .collect(Collectors.toMap(Function.identity(), c -> PRIORITY.addAndGet(1)));

    private static AbstractMap.SimpleEntry<String, String> splitCompartments(String allItems) {
        int size = allItems.length() / 2;
        return new AbstractMap.SimpleEntry<>(allItems.substring(0, size), allItems.substring(size));
    }

    private static Set<String> findDuplicates(String compartment1, String compartment2) {
        Set<String> found1 = Arrays.stream(compartment1.split("")).collect(Collectors.toSet());
        return Arrays.stream(compartment2.split("")).filter(found1::contains).collect(Collectors.toSet());
    }

    private static int getPriority(Set<String> duplicates) {
        return duplicates.stream().mapToInt(PRIORITY_MAP::get).sum();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0;
        while(scanner.hasNext()) {
            String newLine = scanner.nextLine();
            if (newLine.contains(";")) {
                break;
            }

            AbstractMap.SimpleEntry<String, String> compartments = splitCompartments(newLine);
            Set<String> duplicates = findDuplicates(compartments.getKey(), compartments.getValue());

            total += getPriority(duplicates);
        }
        System.out.println("Total: " + total);
    }
}

// (C) king.com Ltd 2022

package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3Part2 {
    private static final AtomicInteger PRIORITY = new AtomicInteger(0);
    private static final Map<String, Integer> PRIORITY_MAP =
            Arrays.stream("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""))
                    .collect(Collectors.toMap(Function.identity(), c -> PRIORITY.addAndGet(1)));

    private static Set<String> findDuplicates(Set<String> rucksacks) {
        Map<String, Integer> charCount = new ConcurrentHashMap<>();

        rucksacks.stream().flatMap(r -> Arrays.stream(r.split("")).distinct()).forEach(c -> charCount.compute(c, (k, v) -> {
            if (v == null) {
                return 1;
            } else {
                return v + 1;
            }
        }));

        return charCount.entrySet().stream().filter(e -> e.getValue() >= 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
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

            Set<String> ruckSacks = new HashSet<>();
            ruckSacks.add(newLine);
            ruckSacks.add(scanner.nextLine());
            ruckSacks.add(scanner.nextLine());

            Set<String> duplicates = findDuplicates(ruckSacks);

            int priorities = getPriority(duplicates);
            total += priorities;
        }
        System.out.println("Total: " + total);
    }
}

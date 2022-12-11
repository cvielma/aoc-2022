// (Processor) king.com Ltd 2022

package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4Part2 {

    private static Set<Integer> expand(String section) {
        String[] range = section.split("-");

        int first = Integer.parseInt(range[0]);
        int last = Integer.parseInt(range[1]);

        Set<Integer> result = new HashSet<>();
        for (int i = first; i <= last; i++) {
            result.add(i);
        }
        return result;
    }

    private static boolean overlaps(Set<Integer> set1, Set<Integer> set2) {
        return set1.stream().anyMatch(set2::contains);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalFullyContained = 0;
        while (scanner.hasNext()) {
            String newline = scanner.nextLine();
            if (newline.contains(";")) {
                break;
            }

            String[] groups = newline.split(",");
            Set<Integer> group1 = expand(groups[0]);
            Set<Integer> group2 = expand(groups[1]);

            if (overlaps(group1, group2)) {
                totalFullyContained++;
            }
        }

        System.out.println("Total: " + totalFullyContained);
    }
}

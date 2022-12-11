package com.aoc.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Day10Part1 {
    private static final List<Integer> IMPORTANT_CYCLES = List.of(20, 60, 100, 140, 180, 220);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Processor processor = new Processor();
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                Operation operation = parseOperation(nextLine);
                processor.addOperation(operation);
            }


            List<Integer> values = new ArrayList<>();
            for (Integer i : IMPORTANT_CYCLES) {
                processor.executeUntilCycle(i);
                System.out.println("Processed until: " + i + ", value: " + processor.xValue() + ", signal: " + processor.getSignal(i));
                values.add(processor.getSignal(i));
            }

            int total = values.stream().mapToInt(i -> i).sum();

            System.out.println(total);
        }
    }

    private static Operation parseOperation(String nextLine) {
        Matcher noop = Operation.Noop.PATTERN.matcher(nextLine);
        Matcher addx = Operation.Addx.PATTERN.matcher(nextLine);
        if (noop.matches()) {
            return new Operation.Noop();
        } else if (addx.matches()) {
            return new Operation.Addx(Integer.parseInt(addx.group(1)));
        }
        throw new IllegalArgumentException("Invalid command: " + nextLine);
    }


}

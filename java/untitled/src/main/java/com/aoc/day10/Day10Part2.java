package com.aoc.day10;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Day10Part2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Processor processor = new Processor();
            CRT crt = new CRT();
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                Operation operation = parseOperation(nextLine);
                processor.addOperation(operation);
            }


            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 241; i++) {
                processor.executeUntilCycle(i);
                int spriteMiddle = processor.xValue();
                sb.append(crt.print(spriteMiddle));
                crt.increasePosition();

                if (i % 40 == 0) {
                    sb.append("\n");
                    crt.resetPosition();
                }
            }

            System.out.println(sb);
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

// (C) king.com Ltd 2022

package com.aoc.day5;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5Part2 {

    public static void main(String[] args) {

        CrateMover crateMover = new CrateMover9001();
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Stack<String>> stacks = parseStacks(scanner);

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }
                if (nextLine.isBlank()) {
                    continue;
                }

                Move move = Move.parse(nextLine);
                crateMover.move(stacks, move);
            }

            System.out.println("Result: " + stacks.stream().map(Stack::peek).collect(Collectors.joining("")));
        }
    }

    private static ArrayList<Stack<String>> parseStacks(Scanner scanner) {
        int cratesStacks = -1;
        ArrayList<Stack<String>> invertedStacks = new ArrayList<>();

        // Parse Crates
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (isCrateNumberingLine(nextLine)) {
                break;
            }

            if (cratesStacks < 0) {
                cratesStacks = (nextLine.length() + 1) / 4; // size of crate
                for (int i = 0; i < cratesStacks; i++) {
                    invertedStacks.add(new Stack<>());
                }
            }

            for (int i = 0; i < cratesStacks; i++) {
                Stack<String> currentStack = invertedStacks.get(i);
                parseCrate(nextLine, i).ifPresent(currentStack::push);
            }
        }

        ArrayList<Stack<String>> stacks = new ArrayList<>(invertedStacks.size());
        for (Stack<String> invertedStack : invertedStacks) {
            Stack<String> newStack = new Stack<>();
            int size = invertedStack.size();
            for (int i = 0; i < size; i++) {
                newStack.push(invertedStack.pop());
            }
            stacks.add(newStack);
        }
        return stacks;
    }

    private static Optional<String> parseCrate(String nextLine, int crateNum) {
        int start = crateNum * 4;
        String bracketCrate = nextLine.substring(start, start + 3);
        String parsedCrate = bracketCrate.replaceAll("\\[|\\]", "");
        return parsedCrate.isBlank() ? Optional.empty() : Optional.of(parsedCrate);
    }

    private static boolean isCrateNumberingLine(String nextLine) {
        String s = nextLine.replace(" ", "");
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

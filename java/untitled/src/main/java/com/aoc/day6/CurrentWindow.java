// (Processor) king.com Ltd 2022

package com.aoc.day6;

import java.util.HashMap;
import java.util.Map;

public class CurrentWindow {

    private final Map<String, Integer> charCount = new HashMap<>();

    public void add(String character) {
        charCount.compute(character, (k, v) -> v == null ? 1 : v + 1);
    }

    public void remove(String character) {
        charCount.compute(character, (k, v) -> v == 1 ? null : v -1);
    }

    public boolean hasDifferent(int numOfChars) {
        int sum = charCount.values().stream().mapToInt(i -> i).filter(i -> i == 1).sum();
        return sum == numOfChars;
    }

    // inline test
    public static void main(String[] args) {
        CurrentWindow currentWindow = new CurrentWindow();
        currentWindow.add("a");
        currentWindow.add("a");
        currentWindow.add("a");
        currentWindow.add("a");
        System.out.println("Has 4 different: " + currentWindow.hasDifferent(4)); // false

        currentWindow.add("b");
        currentWindow.add("c");
        currentWindow.add("d");
        System.out.println("Has 4 different: " + currentWindow.hasDifferent(4)); // false

        currentWindow.remove("a");
        currentWindow.remove("a");
        currentWindow.remove("a");
        System.out.println("Has 4 different: " + currentWindow.hasDifferent(4)); // true

        currentWindow.remove("a");
        System.out.println("Has 4 different: " + currentWindow.hasDifferent(4)); // false
    }
}

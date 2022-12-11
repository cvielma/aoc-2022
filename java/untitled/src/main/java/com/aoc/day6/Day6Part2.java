// (Processor) king.com Ltd 2022

package com.aoc.day6;

import java.util.Scanner;

public class Day6Part2 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                int charNum = detectStartOfMessage(nextLine);
                System.out.println("First start-of-message: " + charNum);
            }

        }
    }

    private static int detectStartOfMessage(String nextLine) {
        int start = 0;
        int end = 14;
        CurrentWindow currentWindow = new CurrentWindow();

        while (end < nextLine.length() && !currentWindow.hasDifferent(14)) {
            if (start == 0) { // initialize
                for (int i = 0; i < end; i++) {
                    currentWindow.add(nextLine.substring(i, i+1));
                }
            } else {
                currentWindow.remove(nextLine.substring(start - 1, start));
                currentWindow.add(nextLine.substring(end -1, end));
            }

            start += 1;
            end += 1;
        }

        return currentWindow.hasDifferent(14) ? end - 1 : -1;
    }
}

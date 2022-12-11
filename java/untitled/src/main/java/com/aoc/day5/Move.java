// (Processor) king.com Ltd 2022

package com.aoc.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move {
    private static final Pattern MOVE_PATTERN = Pattern.compile("move ([\\d]+) from ([\\d]+) to ([\\d]+)");
    private final int cratesNum;
    private final int from;
    private final int to;

    public Move(int cratesNum, int from, int to) {
        this.cratesNum = cratesNum;
        this.from = from - 1; // fixing for 0-indexed array
        this.to = to - 1; // fixing for 0-indexed array
    }

    public static Move parse(String string) {
        Matcher matcher = MOVE_PATTERN.matcher(string);
        if (matcher.find()) {
            int cratesNum = Integer.parseInt(matcher.group(1));
            int from = Integer.parseInt(matcher.group(2));
            int to = Integer.parseInt(matcher.group(3));
            return new Move(cratesNum, from, to);
        } else {
            throw new RuntimeException("String doesn't match: " + string);
        }
    }

    public int getCratesNum() {
        return cratesNum;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cratesNum=" + cratesNum +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}

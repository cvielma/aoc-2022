package com.aoc.day10;

import java.util.regex.Pattern;

public interface Operation {
    int value();

    int cycles();

    record Noop(int value) implements Operation {
        public static final Pattern PATTERN = Pattern.compile("noop");
        public Noop() {
            this(0);
        }

        @Override
        public int cycles() {
            return 1;
        }
    }

    record Addx(int value) implements  Operation {
        public static final Pattern PATTERN = Pattern.compile("addx (-?\\d+)");
        @Override
        public int cycles() {
            return 2;
        }
    }
}

package com.aoc.day9;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    UP ("U"),
    DOWN ("D"),
    RIGHT ("R"),
    LEFT ("L");

    private final String letter;

    Direction(String letter) {
        this.letter = letter;
    }

    public static Optional<Direction> getByLetter(String letter) {
        return Arrays.stream(Direction.values()).filter(l -> l.letter.equals(letter)).findFirst();
    }
}

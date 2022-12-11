package com.aoc.day10;

public class CRT {
    private int position = 0;

    public String print(int spriteMiddle) {
        if (position >= spriteMiddle -1 && position <= spriteMiddle + 1) {
            return "#";
        } else {
            return ".";
        }
    }

    public int increasePosition() {
        return position++;
    }

    public void resetPosition() {
        position = 0;
    }
}

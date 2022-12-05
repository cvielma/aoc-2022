// (C) king.com Ltd 2022

package com.aoc.day5;

import java.util.ArrayList;
import java.util.Stack;

public interface CrateMover {

    void move(ArrayList<Stack<String>> stacks, Move move);
}

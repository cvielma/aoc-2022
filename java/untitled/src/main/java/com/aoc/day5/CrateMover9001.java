// (C) king.com Ltd 2022

package com.aoc.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CrateMover9001 implements CrateMover {
    @Override
    public void move(ArrayList<Stack<String>> stacks, Move move) {
        Stack<String> from = stacks.get(move.getFrom());
        Stack<String> to = stacks.get(move.getTo());

        List<String> movingCrates = from.subList(from.size() - move.getCratesNum(), from.size());
        to.addAll(movingCrates);
        for (int i = 0; i < move.getCratesNum(); i++) {
            from.pop();
        }
    }
}

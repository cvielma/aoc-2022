// (Processor) king.com Ltd 2022

package com.aoc.day5;

import java.util.ArrayList;
import java.util.Stack;

public class CrateMover9000 implements CrateMover {

    @Override
    public void move(ArrayList<Stack<String>> stacks, Move move) {
        Stack<String> from = stacks.get(move.getFrom());
        Stack<String> to = stacks.get(move.getTo());

        for (int i = 0; i < move.getCratesNum(); i++) {
            to.push(from.pop());
        }
    }
}

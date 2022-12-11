package com.aoc.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey2 {
    private List<Integer> initialWorry;
    private Function<Integer, Integer> operation;
    private Function<Integer, Integer> test;

    private int itemsInspected;

    public Monkey2(List<Integer> initialWorry, Function<Integer, Integer> operation, Function<Integer, Integer> test) {
        this.initialWorry = new ArrayList<>(initialWorry);
        this.operation = operation;
        this.test = test;
        this.itemsInspected = 0;
    }

    public void addToMonkey(int worryLevel) {
        this.initialWorry.add(worryLevel);
    }

    public void doTurn(List<Monkey2> monkeys) {
        while(!initialWorry.isEmpty()) {
            Integer currentItem = initialWorry.remove(0);
            Integer worryLevel = operation.apply(currentItem);
//            worryLevel = worryLevel / 10;
            Integer monkey = test.apply(worryLevel);
            monkeys.get(monkey).addToMonkey(worryLevel);
            itemsInspected++;
        }
    }

    public int itemsInspected() {
        return itemsInspected;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "initialWorry=" + initialWorry +
                "itemsInspected=" + itemsInspected +
                '}';
    }
}

package com.aoc.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey2 {
    private List<Long> initialWorry;
    private Function<Long, Long> operation;
    private Function<Long, Integer> test;
    private final int lcm;

    private long itemsInspected;

    public Monkey2(List<Long> initialWorry, Function<Long, Long> operation, Function<Long, Integer> test, int lcm) {
        this.initialWorry = new ArrayList<>(initialWorry);
        this.operation = operation;
        this.test = test;
        this.lcm = lcm;
        this.itemsInspected = 0;
    }

    public void addToMonkey(long worryLevel) {
        this.initialWorry.add(worryLevel);
    }

    public void doTurn(List<Monkey2> monkeys) {
        while(!initialWorry.isEmpty()) {
            Long currentItem = initialWorry.remove(0);
            Long worryLevel = operation.apply(currentItem);
            worryLevel = worryLevel % lcm;
            Integer monkey = test.apply(worryLevel);
            monkeys.get(monkey).addToMonkey(worryLevel);
            itemsInspected++;
        }
    }

    public long itemsInspected() {
        return itemsInspected;
    }

    @Override
    public String toString() {
        return "Monkey2{" +
                "initialWorry=" + initialWorry +
                ", itemsInspected=" + itemsInspected +
                '}';
    }
}

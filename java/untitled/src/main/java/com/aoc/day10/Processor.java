package com.aoc.day10;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private int expectedCycle = 1;
    private int x = 1;
    private List<PendingExecution> pendingExecutions = new ArrayList<>();

    private record PendingExecution(int cycle, Operation operation) {}

    public void addOperation(Operation operation) {
        expectedCycle += operation.cycles();
        pendingExecutions.add(new PendingExecution(expectedCycle, operation));

    }

    public void executeUntilCycle(int expectedCycle) {
        while (!pendingExecutions.isEmpty() && pendingExecutions.get(0).cycle() <= expectedCycle) {
            x += pendingExecutions.remove(0).operation().value();
        }
    }

    public int xValue() {
        return x;
    }

    public int getSignal(int expectedCycle) {
        return expectedCycle * x;
    }
}

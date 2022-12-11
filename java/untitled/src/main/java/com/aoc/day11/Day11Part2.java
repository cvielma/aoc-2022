package com.aoc.day11;

import java.math.BigInteger;
import java.util.List;

public class Day11Part2 {

    public static void main(String[] args) {
        List<Monkey2> monkeys = List.of(
                new Monkey2(List.of(79, 98), i -> i * 19, i -> i % 23 == 0 ? 2 : 3),
                new Monkey2(List.of(54, 65, 75, 74), i -> i + 6, i -> i % 19 == 0 ? 2 : 0),
                new Monkey2(List.of(79, 60, 97), i -> i * i, i -> i % 13 == 0 ? 1 : 3),
                new Monkey2(List.of(74), i -> i + 3, i -> i % 17 == 0 ? 0 : 1)
        );

//        List<Monkey> monkeys = List.of(
//                new Monkey(List.of(66, 71, 94), i -> i * 5, i -> i % 3 == 0 ? 7 : 4),
//                new Monkey(List.of(70), i -> i + 6, i -> i % 17 == 0 ? 3 : 0),
//                new Monkey(List.of(62, 68, 56, 65, 94, 78), i -> i + 5, i -> i % 2 == 0 ? 3 : 1),
//                new Monkey(List.of(89, 94, 94, 67), i -> i + 2, i -> i % 19 == 0 ? 7 : 0),
//                new Monkey(List.of(71, 61, 73, 65, 98, 98, 63), i -> i * 7, i -> i % 11 == 0 ? 5 : 6),
//                new Monkey(List.of(55, 62, 68, 61, 60), i -> i + 7, i -> i % 5 == 0 ? 2 : 1),
//                new Monkey(List.of(93, 91, 69, 64, 72, 89, 50, 71), i -> i + 1, i -> i % 13 == 0 ? 5 : 2),
//                new Monkey(List.of(76, 50), i -> i * i, i -> i % 7 == 0 ? 4 : 6)
//        );

        for (int i = 1; i < 10001; i++) {
            for (Monkey2 monkey : monkeys) {
                monkey.doTurn(monkeys);
            }

            if (i == 1 || i == 20 || i == 1000 || i == 2000 || i == 3000 || i == 5000 || i == 6000 || i == 7000 || i == 8000 || i == 9000 || i == 10000)
            System.out.println("Result after turn " + i + ":" + monkeys);
        }

        long monkeyBusiness = monkeys.stream()
                .sorted((o1, o2) -> (o2.itemsInspected() - o1.itemsInspected()) > 0 ? 1 : -1)
                .limit(2)
                .mapToLong(Monkey2::itemsInspected)
                .reduce(1, (acc, value) -> acc * value);

        System.out.println("Monkey business: " + monkeyBusiness);
    }
}

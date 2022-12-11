package com.aoc.day11;

import java.util.List;

public class Day11Part2 {

    public static void main(String[] args) {
//        List<Monkey2> monkeys = List.of(
//                new Monkey2(List.of(79l, 98l), i -> i * 19, i -> i % 23 == 0 ? 2 : 3, 96577),
//                new Monkey2(List.of(54l, 65l, 75l, 74l), i -> i + 6, i -> i % 19 == 0 ? 2 : 0, 96577),
//                new Monkey2(List.of(79l, 60l, 97l), i -> i * i, i -> i % 13 == 0 ? 1 : 3, 96577),
//                new Monkey2(List.of(74l), i -> i + 3, i -> i % 17 == 0 ? 0 : 1, 96577)
//        );

        List<Monkey2> monkeys = List.of(
                new Monkey2(List.of(66l, 71l, 94l), i -> i * 5, i -> i % 3 == 0 ? 7 : 4, 9_699_690),
                new Monkey2(List.of(70l), i -> i + 6, i -> i % 17 == 0 ? 3 : 0, 9_699_690),
                new Monkey2(List.of(62l, 68l, 56l, 65l, 94l, 78l), i -> i + 5, i -> i % 2 == 0 ? 3 : 1, 9_699_690),
                new Monkey2(List.of(89l, 94l, 94l, 67l), i -> i + 2, i -> i % 19 == 0 ? 7 : 0, 9_699_690),
                new Monkey2(List.of(71l, 61l, 73l, 65l, 98l, 98l, 63l), i -> i * 7, i -> i % 11 == 0 ? 5 : 6, 9_699_690),
                new Monkey2(List.of(55l, 62l, 68l, 61l, 60l), i -> i + 7, i -> i % 5 == 0 ? 2 : 1, 9_699_690),
                new Monkey2(List.of(93l, 91l, 69l, 64l, 72l, 89l, 50l, 71l), i -> i + 1, i -> i % 13 == 0 ? 5 : 2, 9_699_690),
                new Monkey2(List.of(76l, 50l), i -> i * i, i -> i % 7 == 0 ? 4 : 6, 9_699_690)
        );

        for (int i = 1; i < 10001; i++) {
            for (Monkey2 monkey : monkeys) {
                monkey.doTurn(monkeys);
            }

            if (i == 1 || i == 20 || i == 1000 || i == 2000 || i == 3000 || i == 5000 || i == 6000 || i == 7000 || i == 8000 || i == 9000 || i == 10000) {
                System.out.println("Result after turn " + i + ":" + monkeys);
            }
        }

        long monkeyBusiness = monkeys.stream()
                .sorted((o1, o2) -> (o2.itemsInspected() - o1.itemsInspected()) > 0 ? 1 : -1)
                .limit(2)
                .mapToLong(Monkey2::itemsInspected)
                .reduce(1, (acc, value) -> acc * value);

        System.out.println("Monkey business: " + monkeyBusiness);
    }
}

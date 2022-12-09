package com.aoc.day9;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day9Part2 {
    public static void main(String[] args) {
        RopeKnot head = new RopeKnot();
        int moveCount = 0;
        List<RopeKnot> ropeKnots = IntStream.range(0, 9).mapToObj(i -> new RopeKnot()).toList();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String newline = scanner.nextLine();

                if (newline.contains(";")) {
                    break;
                }

                Move move = parseMove(newline);

                System.out.println("\n--- MOVE: " + move);
                for (int i = 0; i < move.steps(); i ++) {
                    Position currentPosition = head.getCurrentPosition();

                    switch (move.direction()) {
                        case UP -> head.moveTo(currentPosition.x(), currentPosition.y() - 1);
                        case DOWN -> head.moveTo(currentPosition.x(), currentPosition.y() + 1);
                        case LEFT -> head.moveTo(currentPosition.x() - 1, currentPosition.y());
                        case RIGHT -> head.moveTo(currentPosition.x() + 1, currentPosition.y());
                    }

                    RopeKnot current = head;

                    for (int k = 0; k < ropeKnots.size(); k++) {
                        final RopeKnot ropeKnot = ropeKnots.get(k);
                        ropeKnot.follow(current).ifPresent(p -> ropeKnot.moveTo(p.x(), p.y()));
                        current = ropeKnot;
                    }
                    printGrid(head, ropeKnots, 30, 30, moveCount);
                    moveCount++;
                }

            }
        }

        printRopeKnots(ropeKnots);
    }

    private static void printRopeKnots(List<RopeKnot> ropeKnots) {
        for (int i = 0; i < ropeKnots.size(); i++) {
            System.out.println("** ROPEKNOT (" + i + "): ");
            System.out.println(ropeKnots.get(i));
            System.out.println(ropeKnots.get(i).getVisited().size());
        }
    }

    private static void printGrid(RopeKnot head, List<RopeKnot> ropeKnots, int height, int length, int moveCount) {
        System.out.println("GRID ("+ moveCount + "):");
        StringBuilder sb = new StringBuilder();
        for (int i = -1 * length/2; i < length/2; i++) {
            sb.append("(" + i + ")");
            if (i >= 0) {
                sb.append(" ");
            }
            for (int j = -1 * height/2; j < height/2; j++) {
                boolean set = false;
                if (head.getCurrentPosition().x() == j && head.getCurrentPosition().y() == i) {
                    sb.append("H");
                    set = true;
                }

                if (!set) {
                    for (int k = 0; k < ropeKnots.size(); k++) {
                        RopeKnot currRopeKnot = ropeKnots.get(k);
                        if (currRopeKnot.getCurrentPosition().x() == j && currRopeKnot.getCurrentPosition().y() == i) {
                            sb.append(k + 1);
                            set = true;
                            break;
                        }
                    }
                }

                if (!set) {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static Move parseMove(String newline) {
        String[] parsedLine = newline.split(" ");

        return new Move(Direction.getByLetter(parsedLine[0]).orElseThrow(), Integer.parseInt(parsedLine[1]));
    }

    private record Move (Direction direction, int steps) {
    }
}

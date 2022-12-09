package com.aoc.day9;

import java.util.Scanner;

public class Day9Part1 {

    public static void main(String[] args) {
        RopeKnot head = new RopeKnot();
        RopeKnot ropeKnot = new RopeKnot();
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

                    ropeKnot.follow(head).ifPresent(p -> ropeKnot.moveTo(p.x(), p.y()));

                    System.out.println("HEAD: " + head.getCurrentPosition());
                    System.out.println("TAIL: " + ropeKnot.getCurrentPosition());
                }
            }
        }

        System.out.println(ropeKnot);
        System.out.println(ropeKnot.getVisited().size());
    }

    private static Move parseMove(String newline) {
        String[] parsedLine = newline.split(" ");

        return new Move(Direction.getByLetter(parsedLine[0]).orElseThrow(), Integer.parseInt(parsedLine[1]));
    }

    private record Move (Direction direction, int steps) {
    }

}

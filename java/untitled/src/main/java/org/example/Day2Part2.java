// (C) king.com Ltd 2022

package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Day2Part2 {

    private static final Map<String, Figure> WIN_MAP = Map.of(
            "A", Figure.PAPER,
            "B", Figure.SCISSOR,
            "C", Figure.ROCK);

    private static final Map<String, Figure> LOSE_MAP = Map.of(
            "A", Figure.SCISSOR,
            "B", Figure.ROCK,
            "C", Figure.PAPER);

    private static final Map<String, Figure> DRAW_MAP = Map.of(
            "A", Figure.ROCK,
            "B", Figure.PAPER,
            "C", Figure.SCISSOR);

    private enum Strategy {
        WIN("Z", 6),
        LOSE("X", 0),
        DRAW("Y", 3),
        ;

        private final String name;
        private final int points;

        Strategy(String name, int points) {

            this.name = name;
            this.points = points;
        }

        static Strategy getByName(String name) {
            return Arrays.stream(Strategy.values()).filter(s -> s.name.equals(name)).findFirst().orElseThrow();
        }
    }

    private enum Figure {
        ROCK(1),
        PAPER(2),
        SCISSOR(3),
        ;

        private final int points;

        Figure(int points) {

            this.points = points;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalPoints = 0;
        while (scanner.hasNext()) {
            String newline = scanner.nextLine();
            if (newline.contains(";")) {
                break;
            }

            String[] play = newline.split(" ");
            String opponentPlay = play[0];
            Strategy myPlay = Strategy.getByName(play[1].toUpperCase());

            int playPoints = myPlay.points;
            switch (myPlay) {
                case WIN -> playPoints += WIN_MAP.get(opponentPlay).points;
                case LOSE -> playPoints += LOSE_MAP.get(opponentPlay).points;
                case DRAW -> playPoints += DRAW_MAP.get(opponentPlay).points;
            }

            totalPoints += playPoints;
//            System.out.println("Play was: " + newline + ", playPoints: " + playPoints + ", totalPoints: " + totalPoints);

        }

        System.out.println("Total: " + totalPoints);
    }

}

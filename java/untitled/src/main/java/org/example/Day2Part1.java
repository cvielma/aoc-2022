// (C) king.com Ltd 2022

package org.example;

import java.util.Scanner;

public class Day2Part1 {


    private static enum MyPlay {
        X("X", "C", "B", 1),
        Y("Y", "A", "C", 2),
        Z("Z", "B", "A", 3),
        ;


        private final String name;
        private final String beats;
        private final String beatenBy;
        private final int points;

        MyPlay(String name, String beats, String beatenBy, int points) {
            this.name = name;
            this.beats = beats;
            this.beatenBy = beatenBy;
            this.points = points;
        }

        boolean beats(String opponentPlay) {
            return beats.equals(opponentPlay);
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
            MyPlay myPlay = MyPlay.valueOf(play[1].toUpperCase());

            totalPoints +=  myPlay.points;
            if (myPlay.beats(opponentPlay)) {
                totalPoints += 6;
            } else if (!myPlay.beatenBy.equals(opponentPlay)) {
                totalPoints += 3;
            }
        }

        System.out.println("Total: " + totalPoints);
    }

}

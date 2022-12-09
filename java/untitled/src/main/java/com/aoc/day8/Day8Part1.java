package com.aoc.day8;

import java.util.Scanner;

public class Day8Part1 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Grid grid = new Grid();
            int currLine = 0;
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                addToGrid(currLine, nextLine, grid);
                currLine++;
            }

            setMaximumFromLeft(grid);
            setMaximumFromRight(grid);
            setMaximumFromTop(grid);
            setMaximumFromBottom(grid);
            int total = getTotalVisible(grid);

            System.out.println(grid);
            System.out.println(total);
        }
    }

    private static int getTotalVisible(Grid grid) {
        int countVisible = 0;
        for (int i = 0; i < grid.rows(); i++) {
            for (int j = 0; j < grid.cols(); j++) {
                if (grid.getTree(i, j).isVisible()) {
                    countVisible++;
                }
            }
        }
        return countVisible;
    }

    private static void setMaximumFromBottom(Grid grid) {
        for (int j = 0; j < grid.cols(); j++) {
            int currentMax = Integer.MIN_VALUE;
            for (int i = grid.rows() - 1; i >= 0; i--) {
                grid.getTree(i,j).setMaxBottom(currentMax);

                if (grid.getTree(i, j).height() > currentMax) {
                    currentMax = grid.getTree(i, j).height();
                }
            }
        }
    }

    private static void setMaximumFromTop(Grid grid) {
        for (int j = 0; j < grid.cols(); j++) {
            int currentMax = Integer.MIN_VALUE;
            for (int i = 0; i < grid.rows(); i++) {
                grid.getTree(i,j).setMaxTop(currentMax);

                if (grid.getTree(i, j).height() > currentMax) {
                    currentMax = grid.getTree(i, j).height();
                }
            }
        }
    }

    private static void setMaximumFromRight(Grid grid) {
        for (int i = 0; i < grid.rows(); i++) {
            int currentMax = Integer.MIN_VALUE;
            for (int j = grid.cols() - 1; j >= 0; j--) {
                grid.getTree(i,j).setMaxRight(currentMax);

                if (grid.getTree(i, j).height() > currentMax) {
                    currentMax = grid.getTree(i, j).height();
                }
            }
        }
    }

    private static void setMaximumFromLeft(Grid grid) {
        for (int i = 0; i < grid.rows(); i++) {
            int currentMax = Integer.MIN_VALUE;
            for (int j = 0; j < grid.cols(); j++) {
                grid.getTree(i,j).setMaxLeft(currentMax);

                if (grid.getTree(i, j).height() > currentMax) {
                    currentMax = grid.getTree(i, j).height();
                }
            }
        }
    }

    private static void addToGrid(int currLine, String nextLine, Grid grid) {
        for (int i = 0; i < nextLine.length(); i++) {
            int treeSize = Integer.parseInt(nextLine.substring(i, i+1));
            grid.addTree(new Tree(treeSize), currLine, i);
        }
    }
}

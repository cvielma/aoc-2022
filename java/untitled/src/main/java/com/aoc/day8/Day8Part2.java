package com.aoc.day8;

import java.util.Scanner;

public class Day8Part2 {

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

            int total = getMaxScenicScore(grid);

            System.out.println(grid);
            System.out.println(total);
        }
    }

    private static int getMaxScenicScore(Grid grid) {
        int maxScenicScore = 0;
        for (int i = 0; i < grid.rows(); i++) {
            for (int j = 0; j < grid.cols(); j++) {
                int currScenicScore = calculateScenicScoreForTree(i, j, grid);
                if (currScenicScore > maxScenicScore) {
                    maxScenicScore = currScenicScore;
                }
            }
        }
        return maxScenicScore;
    }

    private static int calculateScenicScoreForTree(int x, int y, Grid grid) {
        // Check left
        calculateLeftVisibility(x, y, grid);

        // Check top
        calculateTopVisibility(x, y, grid);

        // Check right
        calculateRightVisibility(x, y, grid);

        // Check bottom
        calculateBottomVisibility(x, y, grid);
        return grid.getTree(x, y).scenicScore();
    }

    private static void calculateBottomVisibility(int x, int y, Grid grid) {
        Tree tree = grid.getTree(x, y);
        int i = y + 1;
        int currHeight = -1;
        int visibility = 0;
        while (i < grid.rows() && tree.height() > currHeight) {
            currHeight = grid.getTree(x, i).height();
            visibility++;
            i++;
        }
        tree.setVisibilityBottom(visibility);
    }

    private static void calculateRightVisibility(int x, int y, Grid grid) {
        Tree tree = grid.getTree(x, y);
        int i = x + 1;
        int currHeight = -1;
        int visibility = 0;
        while (i < grid.rows() && tree.height() > currHeight) {
            currHeight = grid.getTree(i, y).height();
            visibility++;
            i++;
        }
        tree.setVisibilityRight(visibility);
    }

    private static void calculateTopVisibility(int x, int y, Grid grid) {
        Tree tree = grid.getTree(x, y);
        int i = y - 1;
        int currHeight = -1;
        int visibility = 0;
        while (i >= 0 && tree.height() > currHeight) {
            currHeight = grid.getTree(x, i).height();
            visibility++;
            i--;
        }
        tree.setVisibilityTop(visibility);
    }

    private static void calculateLeftVisibility(int x, int y, Grid grid) {
        Tree tree = grid.getTree(x, y);
        int i = x - 1;
        int currHeight = -1;
        int visibility = 0;
        while (i >= 0 && tree.height() > currHeight) {
            currHeight = grid.getTree(i, y).height();
            visibility++;
            i--;
        }
        tree.setVisibilityLeft(visibility);
    }


    private static void addToGrid(int currLine, String nextLine, Grid grid) {
        for (int i = 0; i < nextLine.length(); i++) {
            int treeSize = Integer.parseInt(nextLine.substring(i, i+1));
            grid.addTree(new Tree(treeSize), currLine, i);
        }
    }
}

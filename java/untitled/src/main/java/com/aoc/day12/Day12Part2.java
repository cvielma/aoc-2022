package com.aoc.day12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day12Part2 {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {

            GraphBuilder2 graphBuilder = new GraphBuilder2();
            int row = 0;
            while(scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                parseLine(nextLine, row, graphBuilder);
                row++;
            }


            // Hackerman
            int minimum = Integer.MAX_VALUE;
            for (Node start : graphBuilder.getStart()) {
                start.setDistanceFromStart(0);
                int visitedCount = findRouteBfs(start, graphBuilder.getEnd());
                if (visitedCount < minimum && visitedCount >= 0) {
                    minimum = visitedCount;
                }
            }

            System.out.println("Total visited: " + minimum);
        }
    }

    private static int findRouteBfs(Node start, Node end) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> pending = new LinkedList<>();

        Node currNode = start;

        pending.add(currNode);
        visited.add(currNode);

        while(!pending.isEmpty() && !currNode.equals(end)) {
            currNode = pending.poll();

            for (Node neighbor : currNode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    pending.add(neighbor);
                    neighbor.setDistanceFromStart(currNode.getDistanceFromStart() + 1);
                }
            }
        }

        return currNode.equals(end) ? currNode.getDistanceFromStart() + 1: -1;
    }

    private static void parseLine(String nextLine, int row, GraphBuilder2 graphBuilder) {
        Node previousNode = null;
        for (int col = 0; col < nextLine.length(); col++) {
            String letter = nextLine.substring(col, col + 1);

            Node currNode = graphBuilder.addNode(mapLetter(letter), row, col);

            if ("a".equals(currNode.getLetter())) {
                graphBuilder.addStart(currNode);
            }

            if ("E".equals(letter)) {
                graphBuilder.setEnd(previousNode);
            }
            previousNode = currNode;
        }
    }

    private static String mapLetter(String letter) {
        if ("S".equals(letter)) {
            return "a";
        }

        if ("E".equals(letter)) {
            return "z";
        }

        return letter;
    }
}

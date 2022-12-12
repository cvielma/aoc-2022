package com.aoc.day12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day12Part1 {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {

            GraphBuilder graphBuilder = new GraphBuilder();
            int row = 0;
            while(scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                parseLine(nextLine, row, graphBuilder);
                row++;
            }

            System.out.println("START: " + graphBuilder.getStart());
            int visitedCount = findRouteBfs(graphBuilder.getStart(), graphBuilder.getEnd());

            System.out.println("Total visited: " + visitedCount);
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

    private static void parseLine(String nextLine, int row, GraphBuilder graphBuilder) {
        Node previousNode = null;
        for (int col = 0; col < nextLine.length(); col++) {
            String letter = nextLine.substring(col, col + 1);

            Node currNode = graphBuilder.addNode(letter, row, col);

            if ("S".equals(letter)) {
                graphBuilder.setStart(currNode);
            }

            if ("E".equals(letter)) {
                graphBuilder.setEnd(previousNode);
            }
            previousNode = currNode;
        }
    }
}

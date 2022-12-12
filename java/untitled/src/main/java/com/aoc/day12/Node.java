package com.aoc.day12;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Node {
    private static int ID_GENERATOR = 0;

    private final int id;
    private static final AtomicInteger HEIGHT = new AtomicInteger(0);
    private static final Map<String, Integer> HEIGHT_MAP =
            Arrays.stream("SabcdefghijklmnopqrstuvwxyzE".split(""))
                    .collect(Collectors.toMap(Function.identity(), c -> HEIGHT.addAndGet(1)));
    private final String letter;
    private final int height;

    private final Set<Node> neighbors;

    private int distanceFromStart;

    public Node(String letter) {
        this.letter = letter;
        this.height = HEIGHT_MAP.get(letter);
        this.neighbors = new HashSet<>();
        this.id = ID_GENERATOR++;
    }

    public void addNeighbor(Node node) {
        if (node.getHeight() <= this.height + 1) {
            this.neighbors.add(node);
        }
    }

    public String getLetter() {
        return letter;
    }

    public int getHeight() {
        return height;
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", letter='" + letter + '\'' +
                ", neighbors = [" + neighbors.stream().map(Node::getLetter).collect(Collectors.joining(",")) + "]" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

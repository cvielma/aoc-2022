package com.aoc.day12;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    private final List<List<Node>> nodes;

    private Node start;
    private Node end;

    public GraphBuilder() {
        this.nodes = new ArrayList<>();
    }

    public Node addNode(String letter, int x, int y) {
        Node node = new Node(letter);
        initializePosition(x, y);
        nodes.get(x).set(y, node);

        // add neighbors
        if (x > 0 && nodes.get(x-1) != null && nodes.get(x-1).get(y) != null) {
            node.addNeighbor(nodes.get(x-1).get(y));
            nodes.get(x-1).get(y).addNeighbor(node);
        }

        if (x < nodes.size() - 1 && nodes.get(x + 1) != null && nodes.get(x + 1).get(y) != null) {
            node.addNeighbor(nodes.get(x+1).get(y));
            nodes.get(x+1).get(y).addNeighbor(node);
        }

        if (y > 0 && nodes.get(x).get(y - 1) != null) {
            node.addNeighbor(nodes.get(x).get(y-1));
            nodes.get(x).get(y - 1).addNeighbor(node);
        }

        if (y < nodes.get(x).size() - 1 && nodes.get(x).get(y + 1) != null) {
            node.addNeighbor(nodes.get(x).get(y+1));
            nodes.get(x).get(y+1).addNeighbor(node);
        }

        return node;
    }

    private void initializePosition(int x, int y) {
        if (nodes.size() < x + 1) {
            nodes.add(x, new ArrayList<>());
        }

        if (nodes.get(x).size() < y + 1) {
            for (int i = nodes.get(x).size(); i < y + 1; i++) {
                nodes.get(x).add(null);
            }
        }
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "GraphBuilder{" +
                "nodes=" + nodes +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}

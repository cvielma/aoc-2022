package com.aoc.day8;

import java.util.ArrayList;

public class Grid {

    private ArrayList<ArrayList<Tree>> treeGrid;

    public Grid() {
        this.treeGrid = new ArrayList<>();
    }

    public void addTree(Tree tree, int x, int y) {
        try {
            treeGrid.get(x);
        } catch (IndexOutOfBoundsException e) {
            treeGrid.add(x, new ArrayList<>());
        }

        treeGrid.get(x).add(y, tree);
    }

    public Tree getTree(int x, int y) {
        return treeGrid.get(x).get(y);
    }

    public int rows() {
        return treeGrid.size();
    }

    public int cols() {
        return treeGrid.get(0) == null ? 0 : treeGrid.get(0).size();
    }

    @Override
    public String toString() {
        return "Grid{" +
                "treeGrid=" + treeGrid +
                '}';
    }
}

// (C) king.com Ltd 2022

package com.aoc.day7;

public class File implements FilesystemNode {

    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String prefix) {
        return prefix + name + "(" + size + ")";
    }
}

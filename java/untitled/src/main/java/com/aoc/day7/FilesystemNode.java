

package com.aoc.day7;

public interface FilesystemNode {

    String name();
    int size();

    String toString(String prefix);
}

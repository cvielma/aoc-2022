// (C) king.com Ltd 2022

package com.aoc.day7;

import java.util.regex.Pattern;

public class ChangeDirCommand implements Command {

    public static final Pattern CHANGE_DIR_PATTERN = Pattern.compile("\\$ cd (.+)");
    private final String dir;

    public ChangeDirCommand(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "ChangeDirCommand{" +
                "dir='" + dir + '\'' +
                '}';
    }
}

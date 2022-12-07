// (C) king.com Ltd 2022

package com.aoc.day7;

import java.util.regex.Pattern;

public class ListDirectoryCommand implements Command {
    public static final Pattern LIST_PATTERN = Pattern.compile("\\$ ls");
}

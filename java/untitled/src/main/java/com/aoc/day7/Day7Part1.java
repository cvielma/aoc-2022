// (C) king.com Ltd 2022

package com.aoc.day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7Part1 {

    private static final Pattern DIRECTORY_PATTERN = Pattern.compile("dir (.+)");
    private static final Pattern FILE_PATTERN = Pattern.compile("(\\d+) (.+)");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Directory root = new Directory(null, "/");
            Directory currentDir = root;
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(";")) {
                    break;
                }

                Command line = parseCommand(nextLine);
                if (line instanceof ChangeDirCommand) {
                    currentDir = changeDir(root, currentDir, (ChangeDirCommand) line);
                } else if (line instanceof ListDirectoryCommand) {
                    List<FilesystemNode> fileSystemNodes = parseOutput(currentDir, scanner);
                    for (FilesystemNode filesystemNode : fileSystemNodes) {
                        currentDir.addFile(filesystemNode);
                    }
                }
            }

            Set<Directory> dirs = findAllDirsWithLessThan(100000, root);
            int total = dirs.stream().mapToInt(Directory::size).sum();
            //TODO: traverse root and find nodes with less than 1000000
//            System.out.println(root);
            System.out.println(dirs);
            System.out.println(total);
        }
    }

    private static Set<Directory> findAllDirsWithLessThan(int total, Directory root) {
        Set<Directory> buffer = new HashSet<>();
        if (root.size() < total) {
            buffer.add(root);
        }

        Set<FilesystemNode> dirs = root.getFiles().stream().filter(f -> f instanceof Directory).collect(Collectors.toSet());
        for (FilesystemNode dir : dirs) {
            buffer.addAll(findAllDirsWithLessThan(total, (Directory) dir));
        }
        return buffer;
    }

    private static Directory changeDir(Directory root, Directory currentDir, ChangeDirCommand line) {
        String dir = line.getDir();
        if (dir.equals("/")) {
            currentDir = root;
        } else if (dir.equals("..")) {
            currentDir = currentDir.getParent();
        } else {
            currentDir = currentDir.cd(dir);
        }
        return currentDir;
    }

    private static List<FilesystemNode> parseOutput(Directory currentDir, Scanner scanner) {
        List<FilesystemNode> filesystemNodes = new ArrayList<>();

        while (!scanner.hasNext("\\$")) {
            String nextLine = scanner.nextLine();
            Matcher dirMatcher = DIRECTORY_PATTERN.matcher(nextLine);
            Matcher fileMatcher = FILE_PATTERN.matcher(nextLine);

            if (dirMatcher.matches()) {
                filesystemNodes.add(new Directory(currentDir, dirMatcher.group(1)));
            } else if (fileMatcher.matches()) {
                filesystemNodes.add(new File(fileMatcher.group(2), Integer.parseInt(fileMatcher.group(1))));
            } else {
                throw new IllegalArgumentException("Invalid output: " + nextLine);
            }
        }

        return filesystemNodes;
    }

    private static Command parseCommand(String nextLine) {
        Matcher changeDirMatcher = ChangeDirCommand.CHANGE_DIR_PATTERN.matcher(nextLine);
        Matcher listDirMatcher = ListDirectoryCommand.LIST_PATTERN.matcher(nextLine);

        if (changeDirMatcher.matches()) {
            return new ChangeDirCommand(changeDirMatcher.group(1));
        } else if (listDirMatcher.matches()) {
            return new ListDirectoryCommand();
        } else {
            throw new IllegalArgumentException("Invalid command: " + nextLine);
        }
    }

}

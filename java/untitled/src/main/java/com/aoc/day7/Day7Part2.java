

package com.aoc.day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7Part2 {

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

            int availableSpace = 70000000 - root.size();
            int requiredAdditionalSpace = 30000000 - availableSpace;

            int size = findAllDirsWithMoreThan(requiredAdditionalSpace, root).stream().mapToInt(Directory::size).min().getAsInt();
            System.out.println(size);
        }
    }

    private static Set<Directory> findAllDirsWithMoreThan(int requiredAdditionalSpace, Directory root) {
        Set<Directory> buffer = new HashSet<>();
        if (root.size() > requiredAdditionalSpace) {
            buffer.add(root);
        }

        Set<FilesystemNode> dirs = root.getFiles().stream().filter(f -> f instanceof Directory).collect(Collectors.toSet());
        for (FilesystemNode dir : dirs) {
            buffer.addAll(findAllDirsWithMoreThan(requiredAdditionalSpace, (Directory) dir));
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

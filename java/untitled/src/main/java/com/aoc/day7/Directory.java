

package com.aoc.day7;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Directory implements FilesystemNode {

    private final Directory parent;
    private final String name;
    private final Set<FilesystemNode> files;

    public Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        this.files = new HashSet<>();
    }

    public void addFile(FilesystemNode filesystemNode) {
        this.files.add(filesystemNode);
    }

    public Directory cd(String dir) {
        return (Directory) files.stream().filter(f -> f instanceof Directory).filter(d -> dir.equals(d.name())).findFirst().orElseThrow();
    }

    public Directory getParent() {
        return parent;
    }

    public Set<FilesystemNode> getFiles() {
        return files;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        return files.stream().mapToInt(FilesystemNode::size).sum();
    }

    @Override
    public String toString() {
        return this.toString("");
    }

    @Override
    public String toString(String prefix) {
        return  prefix + (parent != null ? parent.name() + (parent.name().equals("/") ? "" : "/") : "") +
                name + (name.endsWith("/") ? "\n" : "/\n") +
                files.stream().map(f ->  f.toString(prefix + " ")).collect(Collectors.joining("\n"));
    }
}

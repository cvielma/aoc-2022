package com.aoc.day8;

public class Tree {

    private int maxTop;
    private int maxBottom;
    private int maxRight;
    private int maxLeft;

    private int visibilityTop;
    private int visibilityBottom;
    private int visibilityLeft;
    private int visibilityRight;

    private final int height;

    public Tree(int height) {
        this.height = height;
    }

    public void setMaxTop(int maxUp) {
        this.maxTop = maxUp;
    }

    public void setMaxBottom(int maxDown) {
        this.maxBottom = maxDown;
    }

    public void setMaxRight(int maxRight) {
        this.maxRight = maxRight;
    }

    public void setMaxLeft(int maxLeft) {
        this.maxLeft = maxLeft;
    }

    public boolean isVisible() {
        return height > maxTop || height > maxBottom || height > maxLeft || height > maxRight;
    }

    public void setVisibilityTop(int visibilityTop) {
        this.visibilityTop = visibilityTop;
    }

    public void setVisibilityBottom(int visibilityBottom) {
        this.visibilityBottom = visibilityBottom;
    }

    public void setVisibilityLeft(int visibilityLeft) {
        this.visibilityLeft = visibilityLeft;
    }

    public void setVisibilityRight(int visibilityRight) {
        this.visibilityRight = visibilityRight;
    }

    public int scenicScore() {
        return this.visibilityBottom * this.visibilityTop * this.visibilityLeft * this.visibilityRight;
    }

    public int height() {
        return height;
    }

    @Override
    public String toString() {
        return "\nTree{" +
                "maxTop=" + maxTop +
                ", maxBottom=" + maxBottom +
                ", maxRight=" + maxRight +
                ", maxLeft=" + maxLeft +
                ", visibilityTop=" + visibilityTop +
                ", visibilityBottom=" + visibilityBottom +
                ", visibilityLeft=" + visibilityLeft +
                ", visibilityRight=" + visibilityRight +
                ", height=" + height +
                ", visible=" + isVisible() +
                ", scenicScore=" + scenicScore() +
                '}';
    }
}

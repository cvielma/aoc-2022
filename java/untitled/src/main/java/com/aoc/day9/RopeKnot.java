package com.aoc.day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RopeKnot {
    private List<Position> visited;
    private Position currentPosition;

    public RopeKnot() {
        this.visited = new ArrayList<>();
        this.currentPosition = new Position(0,0);

        this.visited.add(currentPosition);
    }

    public void moveTo(int x, int y) {
        this.currentPosition = new Position(x, y);
        this.visited.add(currentPosition);
    }

    public Optional<Position> follow(RopeKnot head) {
        if (!needsToFollow(head)) {
            return Optional.empty();
        }
        Position headPosition = head.getCurrentPosition();

        if (isToTheLeft(headPosition)) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y()));
        } else if (isToTheRight(headPosition)) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y()));
        } else if (isAbove(headPosition)) {
            return Optional.of(new Position(currentPosition.x(), currentPosition.y() - 1));
        } else if (isDown(headPosition)) {
            return Optional.of(new Position(currentPosition.x(), currentPosition.y() + 1));
        } else {
            return moveDiagonal(headPosition);
        }
    }

    private boolean needsToFollow(RopeKnot head) {
        return Math.abs(head.getCurrentPosition().x() - currentPosition.x()) > 1 || Math.abs(head.getCurrentPosition().y() - currentPosition.y()) > 1 ;
    }

    private Optional<Position> moveDiagonal(Position headPosition) {
        if (headPosition.y() == currentPosition.y() + 1 && headPosition.x() == currentPosition.x() + 2) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() + 1 && headPosition.x() == currentPosition.x() -2) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() - 1 && headPosition.x() == currentPosition.x() + 2) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() - 1));
        }


        if (headPosition.y() == currentPosition.y() - 1 && headPosition.x() == currentPosition.x() - 2) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() - 1));
        }

        if (headPosition.y() == currentPosition.y() + 2 && headPosition.x() == currentPosition.x() + 1) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() + 2 && headPosition.x() == currentPosition.x() -1) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() - 2 && headPosition.x() == currentPosition.x() + 1) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() - 1));
        }

        if (headPosition.y() == currentPosition.y() - 2 && headPosition.x() == currentPosition.x() - 1) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() - 1));
        }

        if (headPosition.y() == currentPosition.y() + 2 && headPosition.x() == currentPosition.x() + 2) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() + 2 && headPosition.x() == currentPosition.x() -2) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() + 1));
        }

        if (headPosition.y() == currentPosition.y() - 2 && headPosition.x() == currentPosition.x() + 2) {
            return Optional.of(new Position(currentPosition.x() + 1, currentPosition.y() - 1));
        }

        if (headPosition.y() == currentPosition.y() - 2 && headPosition.x() == currentPosition.x() - 2) {
            return Optional.of(new Position(currentPosition.x() - 1, currentPosition.y() - 1));
        }

        return Optional.empty();
    }

    private boolean isDown(Position headPosition) {
        return headPosition.y() == currentPosition.y() + 2 && headPosition.x() == currentPosition.x();
    }

    private boolean isAbove(Position headPosition) {
        return headPosition.y() == currentPosition.y() - 2 && headPosition.x() == currentPosition.x();
    }

    private boolean isToTheRight(Position headPosition) {
        return headPosition.y() == currentPosition.y() && headPosition.x() == currentPosition.x() + 2;
    }

    private boolean isToTheLeft(Position headPosition) {
        return headPosition.y() == currentPosition.y() && headPosition.x() == currentPosition.x() - 2;
    }

    public Set<Position> getVisited() {
        return new HashSet<>(visited);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public String toString() {
        return "RopeKnot{" +
                "visited=" + visited +
                ", currentPosition=" + currentPosition +
                '}';
    }
}

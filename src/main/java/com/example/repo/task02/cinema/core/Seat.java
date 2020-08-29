package com.example.repo.task02.cinema.core;

public class Seat {
    private final boolean isFree;
    private final int x;
    private final int y;

    public Seat(final boolean isFree, final int x, final int y) {
        this.isFree = isFree;
        this.x = x;
        this.y = y;
    }

    public boolean isFree() {
        return isFree;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return isFree ? "*" : "x";
    }
}

package com.gameoflife;

public class Cell {
    private boolean live;

    public Cell(boolean isLi) {
    }

    public boolean isLive() {
        return live;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "live=" + live +
                '}';
    }
}

package com.gameoflife;

import java.util.Objects;

public class Cell {
    private boolean currLiveStatus;

    private boolean futureLiveStatus;

    public Cell(boolean isLive) {
        this.currLiveStatus = isLive;
    }

    public void updateCurrLiveCellFromFuture(){
        this.currLiveStatus = futureLiveStatus;
    }

    public void updateFutureLiveStatus(boolean currStatus) {
        this.futureLiveStatus = currStatus;
    }
    public boolean isLive() {
        return currLiveStatus;
    }


    @Override
    public String toString() {
        return "Cell{" +
                "currLiveStatus=" + currLiveStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return currLiveStatus == cell.currLiveStatus;
    }

    @Override
    public int hashCode() {

        return Objects.hash(currLiveStatus);
    }
}

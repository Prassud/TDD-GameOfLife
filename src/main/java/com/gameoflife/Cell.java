package com.gameoflife;

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
}

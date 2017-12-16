package com.gameoflife.rules;

public class KillIfLiveNeighboursAreMore implements IGameOfLifeRule {

    @Override
    public boolean evaluateToFindCellDeadOrLive(int totalNeighbourLiveCell) {
        if(totalNeighbourLiveCell > 3){
            return true;
        }
        return false;
    }
}

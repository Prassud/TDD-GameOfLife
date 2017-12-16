package com.gameoflife.rules;

public class RemainSame implements IGameOfLifeRule {

    @Override
    public boolean evaluateToFindCellDeadOrLive(int totalNeighbourLiveCell) {
        if(totalNeighbourLiveCell == 2 || totalNeighbourLiveCell == 3){
            return true;
        }
        return false;
    }
}

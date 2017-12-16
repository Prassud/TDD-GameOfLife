package com.gameoflife.rules;

public class MakeCellLiveIfThreeNeighboursBecomesLive implements IGameOfLifeRule {

    @Override
    public boolean evaluateToFindCellDeadOrLive(int totalNeighbourLiveCell) {
        if(totalNeighbourLiveCell == 3){
            return true;
        }
        return false;

    }
}

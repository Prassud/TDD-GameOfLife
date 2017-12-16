package com.gameoflife.rules;

public class GameOfLifeRules {

    public void applyTheRulesToCell(Cell cell, int totalNeighbourLiveCell) {
        if (totalNeighbourLiveCell < 2) {

        }else if(totalNeighbourLiveCell > 3){

        }else if(totalNeighbourLiveCell == 3 || totalNeighbourLiveCell == 2){

        }
    }
}

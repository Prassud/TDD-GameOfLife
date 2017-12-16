package com.gameoflife.rules;

public interface IGameOfLifeRule {
    boolean evaluateToFindCellDeadOrLive(int totalNeighbourLiveCell);
}

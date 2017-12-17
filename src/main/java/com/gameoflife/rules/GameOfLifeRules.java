package com.gameoflife.rules;

import com.gameoflife.Cell;

public final class GameOfLifeRules {

    public static boolean applyTheRulesToCell(Cell cell, int totalNeighbourLiveCell) {
        boolean isCellStateChanged = false;

        if (cell.isLive()) {
            if ((totalNeighbourLiveCell < 2 || totalNeighbourLiveCell > 3)) {
                cell.updateFutureLiveStatus(false);
                isCellStateChanged = true;
            }
        } else {
            if (totalNeighbourLiveCell == 3) {
                cell.updateFutureLiveStatus(true);
                isCellStateChanged = true;
            }
        }

        return isCellStateChanged;
    }
}

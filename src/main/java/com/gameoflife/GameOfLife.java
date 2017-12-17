package com.gameoflife;

import com.gameoflife.rules.*;

import java.util.LinkedList;
import java.util.List;

public class GameOfLife {
    private Grid grid;

    private List<Cell> nextGenModifiedCells;

    public GameOfLife(Grid grid) throws InvalidGridException {
        if(null == grid) {
            throw new InvalidGridException("Grid is null");
        }
        this.grid = grid;
        this.nextGenModifiedCells = new LinkedList<>();
    }

    public void tickToNextGeneration() {
        int maxGridLength = this.grid.getTotalGridSize();
        for (int gridRowIdx = 0; gridRowIdx < maxGridLength; gridRowIdx++) {
            for (int gridColIdx = 0; gridColIdx < maxGridLength; gridColIdx++) {
                Cell currCell = this.grid.getGridCell(gridRowIdx, gridColIdx);
                if (currCell == null)
                    currCell = GameOfLifeUtility.createDeadCell();
                this.grid.updateGridCell(currCell, gridRowIdx, gridColIdx);
                int totalNeighboursLive = getTotalNeighboursLive(gridRowIdx, gridColIdx);
                boolean isCellStateChanged = GameOfLifeRules.applyTheRulesToCell(currCell, totalNeighboursLive);
                if (isCellStateChanged)
                    this.nextGenModifiedCells.add(currCell);
            }


        }
        this.nextGenModifiedCells.forEach(eachCell -> {
            eachCell.updateCurrLiveCellFromFuture();
        });
        this.nextGenModifiedCells.clear();


    }

    private int getTotalNeighboursLive(int row, int column) {
        int[][] neighbourCellIndices = GameOfLifeUtility.getNeighbourIndexOfCurrentCell(row, column);
        int totalNeighboursLive = 0;
        for (int[] eachNeighbourIndex : neighbourCellIndices) {
            int eachNeighbourIndexRow = eachNeighbourIndex[0];
            int eachNeighbourIndexColumn = eachNeighbourIndex[1];
            boolean isValidNeighbour = GameOfLifeUtility.isValidIndex(eachNeighbourIndexRow, eachNeighbourIndexColumn, this.grid.getTotalGridSize());
            if (isValidNeighbour) {
                Cell neighbourCell = this.grid.getGridCell(eachNeighbourIndexRow, eachNeighbourIndexColumn);
                if (null != neighbourCell && neighbourCell.isLive())
                    totalNeighboursLive++;
            }

        }
        return totalNeighboursLive;
    }


}

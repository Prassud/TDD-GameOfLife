package com.gameoflife;

import com.gameoflife.rules.IGameOfLifeRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {

    private Cell[][] gridCells;

    public Grid(String[] cellPositions, int maxGridSize) {

        this.gridCells = new Cell[maxGridSize][maxGridSize];
        Arrays.stream(cellPositions).forEach(cellPosition -> {
            createCellBasedOnIndex(cellPosition);
        });
    }

    private void createCellBasedOnIndex(String cellPositon) {
        String[] indexes = cellPositon.split(",");
        int row = Integer.parseInt(indexes[0].trim());
        int column = Integer.parseInt(indexes[1].trim());
        gridCells[row][column] = GameOfLifeUtility.createLiveCell();
    }


    public List<Cell> getLiveCells() {
        List<Cell> liveCellList = new ArrayList<Cell>(10);
        Arrays.stream(this.gridCells).forEach(eachRowGridCell -> {
            liveCellList.addAll(Arrays.stream(eachRowGridCell).filter(eachCell ->
                    eachCell != null && eachCell.isLive()).collect(Collectors.toList()));

        });
        return liveCellList;
    }

    public void applyRulesOnCell(List<IGameOfLifeRule> rules) {
        for (int gridRowIdx = 0; gridRowIdx < this.gridCells.length; gridRowIdx++) {
            for (int gridColIdx = 0; gridColIdx < this.gridCells.length; gridColIdx++) {
                Cell currCell = this.gridCells[gridRowIdx][gridColIdx];
                int totalNeighboursLive = getTotalNeighboursLive(gridRowIdx, gridColIdx);
                rules.forEach(eachRule -> eachRule.evaluateToFindCellDeadOrLive(totalNeighboursLive));
            }
        }


    }

    private int getTotalNeighboursLive(int rowIdx, int colIdx) {
        int[][] neighbourCellIndices = GameOfLifeUtility.getNeighBourIndexOfCurrentCell(row, column);
        int totalNeighboursLive = 0;
        for (int[] eachNeighbourIndex : neighbourCellIndices) {
            int eachNeighbourIndexRow = eachNeighbourIndex[0];
            int eachNeighbourIndexColumn = eachNeighbourIndex[1];
            boolean isValidNeighbour = GameOfLifeUtility.isValidIndex(eachNeighbourIndexRow, eachNeighbourIndexColumn, cells.length);
            if (isValidNeighbour) {
                Cell neighbourCell = this.gridCells[eachNeighbourIndexRow][eachNeighbourIndexColumn];
                if (null != neighbourCell && neighbourCell.isLive())
                    totalNeighboursLive++;
            }

        }
        return totalNeighboursLive;
    }
}

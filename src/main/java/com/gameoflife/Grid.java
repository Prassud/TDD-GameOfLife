package com.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {

    private Cell[][] gridCells;

    public int getTotalGridSize() {
        return gridCells.length;
    }

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
        List<Cell> liveCellList = new ArrayList<>(10);
        Arrays.stream(this.gridCells).forEach(eachRowGridCell -> {
            liveCellList.addAll(Arrays.stream(eachRowGridCell).filter(eachCell ->
                    eachCell != null && eachCell.isLive()).collect(Collectors.toList()));

        });
        return liveCellList;
    }


    public Cell getGridCell(int gridRowIdx, int gridColIdx) {
        return this.gridCells[gridRowIdx][gridColIdx];
    }
    public void updateGridCell(Cell cell , int gridRowIdx, int gridColIdx) {
        this.gridCells[gridRowIdx][gridColIdx] = cell;
    }
}

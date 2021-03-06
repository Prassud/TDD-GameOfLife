package com.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Grid {

    private Cell[][] gridCells;

    public int getTotalGridSize() {
        return gridCells.length;
    }

    public Grid(String[] cellPositions, int maxGridSize) throws InvalidGridException, InvalidGridIndexException {

        if (maxGridSize < 1) {
            throw new InvalidGridException("Grid Size Cant be 0 or Less than 0");
        }

        this.gridCells = new Cell[maxGridSize][maxGridSize];



        Arrays.stream(cellPositions).forEach((String cellPosition) -> {
            try {
                createCellBasedOnIndex(cellPosition);
            } catch (InvalidGridIndexException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void createCellBasedOnIndex(String cellPositon) throws InvalidGridIndexException {
        String[] indexes = cellPositon.split(",");
        int row = Integer.parseInt(indexes[0].trim());
        int column = Integer.parseInt(indexes[1].trim());
        boolean validIndex = GameOfLifeUtility.isValidIndex(row, column, getTotalGridSize());
        if (!validIndex)
            throw new InvalidGridIndexException("Invalid Index Provided");
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

    public void updateGridCell(Cell cell, int gridRowIdx, int gridColIdx) {
        this.gridCells[gridRowIdx][gridColIdx] = cell;
    }

    public String getGridCellOutput() {
        StringBuffer buffer = new StringBuffer(10);
        for (Cell[] eachRowCells : gridCells) {
            for (Cell eachCell : eachRowCells) {
                buffer.append(eachCell != null && eachCell.isLive() ? 'X' : ' ');
            }
            buffer.append(System.lineSeparator());
        }
        return buffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return Arrays.equals(gridCells, grid.gridCells);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(gridCells);
    }
}

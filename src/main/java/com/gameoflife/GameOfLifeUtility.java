package com.gameoflife;

public final class GameOfLifeUtility {


    public static Cell createLiveCell() {
        return new Cell(true);
    }

    public static Cell createDeadCell() {
        return new Cell(false);
    }

    public static int[][] getNeighBourIndexOfCurrentCell(int row, int column) {
        int rightColumn = column - 1;
        int leftColumn = column + 1;
        int upRow = row - 1;
        int downRow = row + 1;

        int[] rightCell = new int[]{row, rightColumn};
        int[] leftCell = new int[]{row, leftColumn};
        int[] upCell = new int[]{upRow, column};
        int[] downCell = new int[]{downRow, column};
        int[] rightDown = new int[]{downRow, rightColumn};
        int[] rightUP = new int[]{upRow, rightColumn};
        int[] leftUp = new int[]{upRow, leftColumn};
        int[] leftDown = new int[]{downRow, leftColumn};

        return new int[][]{
                rightCell, leftCell, upCell, downCell, rightDown, rightUP, leftUp, leftDown
        };

    }

    public static boolean isValidIndex(int row, int column, int maxSize) {
        return (row > 0 && row < maxSize) && (column > 0 && column < maxSize);
    }

}

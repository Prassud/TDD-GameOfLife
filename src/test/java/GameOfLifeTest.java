import com.gameoflife.Cell;
import com.gameoflife.GameOfLife;
import com.gameoflife.GameOfLifeUtility;
import com.gameoflife.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(GameOfLifeUtility.class)
public class GameOfLifeTest {
    @Test
    public void verifyIfGridCanBeBuiltWithTheGivenLiveCellPositions() {
        mockStatic(GameOfLifeUtility.class);
        Cell eachCell = mock(Cell.class);
        when(GameOfLifeUtility.createLiveCell()).thenReturn(eachCell);
        when(eachCell.isLive()).thenReturn(true);
        String liveCellPositions[] = new String[]
                {"1, 1", "1, 2", "2, 1", "2, 2"};
        Grid grid = new Grid(liveCellPositions, 4);
        List<Cell> liveCells = grid.getLiveCells();
        liveCells.forEach(a -> Assert.assertEquals(eachCell, a));
        Assert.assertEquals(liveCellPositions.length, liveCells.size());
    }

    @Test
    public void verifyIfFirstRuleIsSatisfedForTheGivenInputs() {

        int[][] neighbourIndices = GameOfLifeUtility.getNeighbourIndexOfCurrentCell(1, 1);
        mockStatic(GameOfLifeUtility.class);
        Cell mockCell = mock(Cell.class);
        Cell mockDeadCell = mock(Cell.class);
        when(GameOfLifeUtility.createLiveCell()).thenReturn(mockCell);
        when(GameOfLifeUtility.getNeighbourIndexOfCurrentCell(anyInt(), anyInt())).thenReturn(neighbourIndices);
        when(mockCell.isLive()).thenReturn(true);
        when(GameOfLifeUtility.createDeadCell()).thenReturn(mockDeadCell);
        when(mockDeadCell.isLive()).thenReturn(false);
        when(GameOfLifeUtility.isValidIndex(anyInt(), anyInt(), anyInt())).thenReturn(true);
        String liveCellPositions[] = new String[]
                {"1, 1"};
        Grid mockGrid = new Grid(liveCellPositions, 10);
        Grid grid = new Grid(liveCellPositions, 10);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertNotEquals(mockGrid, grid);
        verify(mockCell, times(1)).updateFutureLiveStatus(false);

    }

    @Test
    public void verifyIfSecondRuleIsSatisfiedWithCellsInIndexesAreLive() {

        String liveCellPositions[] = new String[]
                {"1,0", "1,1", "1,2", "0,1", "2,1"};
        Grid grid = new Grid(liveCellPositions, 10);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertFalse(grid.getGridCell(1, 1).isLive());
    }


    @Test
    public void verifyIfSecondAndFourthRulesAreSatisfiedWithCellsInIndexesAreLive() {

        String liveCellPositions[] = new String[]
                {"1,0", "1,1", "1,2", "0,1", "2,1"};
        Grid grid = new Grid(liveCellPositions, 10);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertFalse(grid.getGridCell(1, 1).isLive());
        Assert.assertTrue(grid.getGridCell(0, 1).isLive());
    }


    @Test
    public void verifyIfPatternProvidedIsPattern() {

        String expectedResult = "    " + System.lineSeparator() + " XX " + System.lineSeparator() + " XX " + System.lineSeparator() + "    " + System.lineSeparator();

        String liveCellPositions[] = new String[]
                {"1,1", "1,2", "2,1", "2,2"};
        Grid grid = new Grid(liveCellPositions, 4);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertEquals(expectedResult, grid.getGridCellOutput());

    }


    @Test
    public void verifyIfPatternProvidedIsOscillatorPattern() {
        String expectedResult = " X " + System.lineSeparator() + " X " + System.lineSeparator() + " X " + System.lineSeparator();

        String liveCellPositions[] = new String[]
                {"1, 1", "1, 0", "1, 2"};
        Grid grid = new Grid(liveCellPositions, 3);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertEquals(expectedResult, grid.getGridCellOutput());

    }

    @Test
    public void verifyIfPatternProvidedIsToadPattern() {


        String expectedLiveCellPositions[] = new String[]
                {"0, 2", "1, 1", "1, 4", "2, 1", "2, 4", "3, 3"};
        Grid expectedGrid = new Grid(expectedLiveCellPositions, 10);

        String liveCellPositions[] = new String[]
                {"1, 1", "1, 2", "1, 3", "2, 2", "2, 3", "2, 4"};
        Grid grid = new Grid(liveCellPositions, 10);
        GameOfLife gameOfLife = new GameOfLife(grid);
        gameOfLife.tickToNextGeneration();
        Assert.assertEquals(expectedGrid.getGridCellOutput(), grid.getGridCellOutput());

    }


}

import com.gameoflife.Cell;
import com.gameoflife.GameOfLife;
import com.gameoflife.GameOfLifeUtility;
import com.gameoflife.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(GameOfLife.class)
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
    public void verifyIfFirstRuleisSatisifedForTheGivenInputs(){

        String liveCellPositions[] = new String[]
                {"1, 1"};
        Grid grid = new Grid(liveCellPositions,10);
        GameOfLife gameOfLife = new GameOfLife(grid);


    }



    @Test
    public void verifyIfPatternProvidedIsStillPattern(){
        Cell eachCell = mock(Cell.class);
        GameOfLife gameOfLife = mock(GameOfLife.class);
        when(gameOfLife.createLiveCell()).thenReturn(eachCell);
        when(eachCell.isLive()).thenReturn(true);
        String liveCellPositions[] = new String[]
                {"1, 1", "1, 2", "2, 1", "2, 2"};
        Grid grid = new Grid(liveCellPositions, 4);


    }





}

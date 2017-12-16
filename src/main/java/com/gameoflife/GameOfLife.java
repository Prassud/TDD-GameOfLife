package com.gameoflife;

import com.gameoflife.rules.*;

import java.util.List;

public class GameOfLife {
    private Grid grid;

    private List<IGameOfLifeRule> rules;

    public GameOfLife(Grid grid) {
        this.grid = grid;
        rules.add(new KillIfLiveNeighboursAreFewer());
        rules.add(new KillIfLiveNeighboursAreMore());
        rules.add(new MakeCellLiveIfThreeNeighboursBecomesLive());
        rules.add(new RemainSame());


    }

    public void tick() {

        this.grid.applyRulesOnCell(this.rules);

    }


}

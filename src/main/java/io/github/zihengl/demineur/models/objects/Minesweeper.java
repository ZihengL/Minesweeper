package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.enums.Gamestate;
import io.github.zihengl.demineur.models.enums.Status;

public class Minesweeper {

    private static Minesweeper instance;

    public static Minesweeper getInstance() {
        if (instance == null)
            instance = new Minesweeper(Difficulties.BEGINNER);

        return instance;
    }

    // INSTANCE
//    private Gamestate gamestate;
    private Difficulties difficulty;
    private Grid grid;

    private Minesweeper(Difficulties difficulty) {
//        this.gamestate = Gamestate.ONGOING;
        this.setDifficulty(difficulty);
    }

    public Difficulties getDifficulty() {
        return this.difficulty;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setDifficulty(Difficulties difficulty) {
        this.difficulty = difficulty;
        this.grid = new Grid(difficulty.width, difficulty.height, difficulty.mines);
    }

    // METHODS

    public Gamestate dig(int x, int y) {
        Cell cell = this.grid.getCell(x, y);
        cell.dig();

        if (cell.getValue() == Cell.MINE)
            return Gamestate.DEFEAT;
        else if (this.grid.dugCount() == this.grid.area() - this.difficulty.mines)
            return Gamestate.VICTORY;
        else
            return Gamestate.ONGOING;
    }

    public Status toggleFlag(int x, int y) {
        Cell cell = this.grid.getCell(x, y);
        cell.toggleFlag();

        return cell.getStatus();
    }

    public Gamestate check() {
        int threshold = this.grid.area() - this.difficulty.mines;

        int dug = 0, validFlag = 0;
        for (Cell[] column : this.grid.getCells())
            for (Cell cell : column) {
                switch (cell.getStatus()) {
                    case Status.DUG -> {
                        // TODO
                    }
                    case FLAGGED -> {
                        if (cell.getValue() == Cell.MINE &&
                            ++validFlag == this.difficulty.mines)
                            return Gamestate.VICTORY;
                    }
                }
            }
    }
}

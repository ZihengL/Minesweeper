package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.enums.Gamestate;
import io.github.zihengl.demineur.models.observer.Observable;

public class Minesweeper extends Observable {

    public static final Minesweeper instance = new Minesweeper(Difficulties.BEGINNER);

    // INSTANCE
    private Difficulties difficulty;
    private Grid grid;

    private boolean spawned;
    private Gamestate gamestate;

    private Minesweeper(Difficulties difficulty) {
        this.createNewGame(difficulty);
    }

    public Difficulties getDifficulty() {
        return this.difficulty;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void createNewGame(Difficulties difficulty) {
        this.difficulty = difficulty;
        this.grid = new Grid(difficulty.width, difficulty.height);

        this.spawned = false;
        this.gamestate = Gamestate.ONGOING;
    }

    public int area() {
        return this.difficulty.width * this.difficulty.height;
    }

    // METHODS

    public void dig(int x, int y) {
        if (!this.spawned) {
            this.grid.spawn(x, y, this.difficulty);
            this.spawned = true;
        }

        if (!this.gamestate.equals(Gamestate.ONGOING))
            return;

        this.grid.dig(x, y);
        this.gamestate = this.check(x, y);
        this.notifyObserver();
    }

    public void toggleFlag(int x, int y) {
        if (!this.gamestate.equals(Gamestate.ONGOING))
            return;

        this.grid.toggleFlag(x, y);
        this.notifyObserver();
    }

    public Gamestate check(int x, int y) {
        if (this.grid.getCell(x, y).isFatal())
            return Gamestate.DEFEAT;

        if (this.grid.getDugCount() == this.area() - this.difficulty.mines)
            return Gamestate.VICTORY;

        return Gamestate.ONGOING;
    }
}

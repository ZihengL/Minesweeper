package io.github.zihengl.demineur.game.objects;

import io.github.zihengl.demineur.game.enums.Difficulties;

public class Grid {

    private static Grid instance;

    public static Grid getInstance() {
        if (Grid.instance == null)
            Grid.instance = new Grid();

        return Grid.instance;
    }

    // INSTANCE

    private Difficulties difficulty;
    private Cell[][] cells;

    private Grid() {
        this.setDifficulty(Difficulties.BEGINNER);
    }

    public Difficulties getDifficulty() {
        return this.difficulty;
    }

    public int getWidth() {
        return this.cells[0].length;
    }

    public int getHeight() {
        return this.cells.length;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Cell getCell(int x, int y) {
        return this.cells[y][x];
    }

    public void setDifficulty(Difficulties difficulty) {
        this.difficulty = difficulty;
        int width = difficulty.width, height = difficulty.height;

        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.cells[y][x] = new Cell(x, y);
    }

    // METHODS

    public int clampOnX(int x) {
        return Math.min(Math.max(0, x), this.getWidth());
    }

    public int clampOnY(int y) {
        return Math.min(Math.max(0, y), this.getHeight());
    }

    
}

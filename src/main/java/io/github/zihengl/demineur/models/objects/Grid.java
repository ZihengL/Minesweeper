package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.enums.Status;

import java.util.Random;

public class Grid {

    public final Cell[][] cells;

    private int dug;
    private int flags;

    public Grid(int width, int height) {
        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.cells[y][x] = new Cell(x, y);

        this.dug = 0;
        this.flags = 0;
    }

    public Cell getCell(int x, int y) {
        return this.cells[y][x];
    }

    public int getWidth() {
        return this.cells[0].length;
    }

    public int getHeight() {
        return this.cells.length;
    }

    public int getDugCount() {
        return this.dug;
    }

    public int getFlagsCount() {
        return this.flags;
    }

    // METHODS

    public void spawn(int x, int y, Difficulties difficulty) {
        Random randomizer = new Random();
        int placed = 0;

        while (placed < difficulty.mines) {
            int yr = randomizer.nextInt(difficulty.height),
                xr = randomizer.nextInt(difficulty.width);

            if (!(this.cells[yr][xr].isValue(Cell.MINE) || xr == x && yr == y)) {
                this.cells[yr][xr].setValue(Cell.MINE);
                placed++;
            }
        }

        for (Cell[] row : this.cells)
            for (Cell cell : row)
                if (!cell.isValue(Cell.MINE))
                    cell.setValue(cell.countSurroundingMines(this));
    }

    public void dig(int x, int y) {
        Cell cell = this.cells[y][x];
        if (!cell.isStatus(Status.BURIED)) return;

        cell.dig();
        if (cell.isValue(Cell.CLEAR)) {
            int minY = Math.max(y - 1, 0),
                maxY = Math.min(y + 1, this.getHeight() - 1),
                minX = Math.max(x - 1, 0),
                maxX = Math.min(x + 1, this.getWidth() - 1);

            for (int y2 = minY; y2 <= maxY; y2++)
                for (int x2 = minX; x2 <= maxX; x2++)
                    this.dig(x2, y2);
        }

        this.dug++;
    }

    public boolean toggleFlag(int x, int y) {
        Cell cell = this.cells[y][x];
        if (cell.toggleFlag()) {
            this.flags += cell.isStatus(Status.FLAGGED) ? 1 : -1;
            return true;
        }

        return false;
    }
}

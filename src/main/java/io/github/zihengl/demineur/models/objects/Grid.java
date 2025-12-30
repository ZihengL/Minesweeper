package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Status;

import java.util.Random;

public class Grid {

    public final Cell[][] cells;

    public Grid(int width, int height, int mines) {
        this.cells = new Cell[height][width];

        Random randomizer = new Random();
        int placed = 0;
        while (placed < mines) {
            int y = randomizer.nextInt(height),
                x = randomizer.nextInt(width);

            if (this.cells[y][x] == null) {
                this.cells[y][x] = new Cell(x, y, Cell.MINE);
                placed++;
            }
        }

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (this.cells[y][x] == null) {
                    Cell cell = new Cell(x, y);
                    cell.setValue(this);

                    this.cells[y][x] = cell;
                }
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

    // METHODS

    public int clampOnX(int x) {
        return Math.min(Math.max(0, x), this.getWidth());
    }

    public int clampOnY(int y) {
        return Math.min(Math.max(0, y), this.getHeight());
    }

    public int dugCount() {
        int dug = 0;

        for (int y = 0; y < this.getHeight(); y++)
            for (int x = 0; x < this.getWidth(); x++)
                if (this.cells[y][x].getStatus().equals(Status.DUG))
                    dug++;

        return dug;
    }

    public int area() {
        return this.getWidth() * this.getHeight();
    }
}

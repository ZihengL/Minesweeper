package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Status;

public class Cell {

    public static final int MINE = -1;

    public final int x;
    public final int y;

    private int value;
    private Status status;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        this.status = Status.BURIED;
    }

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;

        this.status = Status.BURIED;
    }

    public int getValue() {
        return this.value;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setValue(Grid grid) {
        int mines = this.scanSurrounding(grid);
        this.value = mines;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // METHODS

    public void dig() {
        if (this.status.equals(Status.BURIED))
            this.status = Status.DUG;
    }

    public Status toggleFlag() {
        if (this.status.equals(Status.BURIED))
            this.status = Status.FLAGGED;

        if (this.status.equals(Status.FLAGGED))
            this.status = Status.BURIED;

        return this.status;
    }

    public int scanSurrounding(Grid grid) {
        int minY = Math.max(this.y - 1, 0),
            maxY = Math.min(this.y + 1, grid.getHeight()),
            minX = Math.max(this.x - 1, 0),
            maxX = Math.min(this.x + 1, grid.getWidth()),
            mines = 0;

        for (int y = minY; y < maxY; y++)
            for (int x = minX; x < maxX; x++) {
                Cell cell = grid.getCell(x, y);

                if (cell != null && cell.value == Cell.MINE)
                    mines++;
            }

        return mines;
    }


}

package io.github.zihengl.demineur.game.objects;

import io.github.zihengl.demineur.game.enums.Status;

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

    public int getValue() {
        return this.value;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setValue() {

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // METHODS

    public void dig() {
        if (this.status.equals(Status.BURIED))
            this.setStatus(Status.DUG);
    }

    public void toggleFlag() {
        if (this.status.equals(Status.BURIED))
            this.setStatus(Status.FLAGGED);

        if (this.status.equals(Status.FLAGGED))
            this.setStatus(Status.BURIED);
    }

    public int scanSurrounding(Grid grid) {
        int minY = Math.max(this.y - 1, 0),
            maxY = Math.min(this.y + 1, grid.getHeight()),
            minX = Math.max(this.x - 1, 0),
            maxX = Math.min(this.x + 1, grid.getWidth()),
            mines = 0;

        for (int y = minY; y < maxY; y++)
            for (int x = minX; x < maxX; x++)
                if (grid.getCell(x, y).value == Cell.MINE)
                    mines++;

        return mines;
    }


}

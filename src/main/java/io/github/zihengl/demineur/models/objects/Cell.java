package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.enums.Status;
import io.github.zihengl.demineur.models.observer.Observable;

public class Cell extends Observable {

    public static final int CLEAR = 0;
    public static final int MINE = -1;

    public final int x;
    public final int y;

    private int value;
    private Status status = Status.BURIED;

    public Cell(int x, int y) {
        this(x, y, 0);
    }

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean isStatus(Status status) {
        return this.status.equals(status);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isValue(int value) {
        return this.value == value;
    }

    public boolean isFatal() {
        return this.isValue(MINE) && this.isStatus(Status.DUG);
    }

    // METHODS

    public void dig() {
        this.status = Status.DUG;

        this.notifyObserver();
    }

    public void toggleFlag() {
        switch (this.status) {
            case DUG -> { return; }
            case BURIED -> this.status = Status.FLAGGED;
            default -> this.status = Status.BURIED;
        }

        this.notifyObserver();
    }

    public int countSurroundingMines(Grid grid) {
        int minY = Math.max(this.y - 1, 0),
            maxY = Math.min(this.y + 1, grid.getHeight() - 1),
            minX = Math.max(this.x - 1, 0),
            maxX = Math.min(this.x + 1, grid.getWidth() - 1);

        int mines = 0;
        for (int y = minY; y <= maxY; y++)
            for (int x = minX; x <= maxX; x++)
                mines += grid.getCell(x, y).value == MINE ? 1 : 0;

        return mines;
    }
}

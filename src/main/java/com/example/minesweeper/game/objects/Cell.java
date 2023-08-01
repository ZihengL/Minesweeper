package com.example.minesweeper.game.objects;

import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.minesweeper.game.others.Cells;

public class Cell extends Point {

	private static final long serialVersionUID = 7177360870513949869L;

	public static final int MINE_VALUE = 10;
	public static final int DEF_VALUE = 0;
	public static final int ERR_VALUE = -1;
	public static final boolean DEF_HIDDEN = true;
	public static final boolean DEF_MARK = false;

	protected static GridManager grid;
	
	protected int value;
	protected boolean hidden;
	protected boolean marked;

	// CONSTRUCTORS

	public Cell() {
		this(0, 0, DEF_VALUE);
	}

	public Cell(int x, int y) {
		this(x, y, DEF_VALUE, DEF_HIDDEN, DEF_MARK);
	}

	public Cell(int x, int y, int value) {
		this(x, y, value, DEF_HIDDEN, DEF_MARK);
	}

	public Cell(int x, int y, int value, boolean hidden, boolean marked) {
		super(x, y);
		
		this.setValue(value);
		this.hidden = hidden;
		this.marked = marked;
	}

	// GETTERS

	public int getValue() {
		return this.value;
	}

	public boolean isHidden() { return this.hidden; }

	public boolean isMarked() { return this.marked; }
	
	// SETTERS

	public void setValue(int value) {
		this.value = value;
	}
	
	// RELATIONAL DIMENSIONS & ADJACENCY

	public int minEdgeX() {
		return Math.max(x - 1, 0);
	}

	public int maxEdgeX() {
		return Math.min(x + 1, grid.rows - 1);
	}

	public int minEdgeY() {
		return Math.max(y - 1, 0);
	}

	public int maxEdgeY() {
		return Math.min(y + 1, grid.columns - 1);
	}

	public Cell getAdjacentMin() {
		return grid.get(this.minEdgeX(), this.minEdgeY());
	}

	public Cell getAdjacentMax() {
		return grid.get(this.maxEdgeX(), this.maxEdgeY());
	}

	public int adjacentWidth() {
		return Cells.unitsBetween(this.minEdgeX(), this.maxEdgeX());
	}

	public int adjacentHeight() {
		return Cells.unitsBetween(this.minEdgeY(), this.maxEdgeY());
	}

	public int adjacentArea() {
		return this.adjacentWidth() * this.adjacentHeight();
	}

	public int widthBetween(Cell cell) {
		return Cells.unitsBetween(x, cell.x);
	}

	public int heightBetween(Cell cell) {
		return Cells.unitsBetween(y, cell.y);
	}

	public int areaBetween(Cell cell) {
		return this.widthBetween(cell) * this.heightBetween(cell);
	}

	public boolean isAdjacentTo(Cell cell) {
		return Cells.isWithinRange(cell.x, this.minEdgeX(), this.maxEdgeX()) &&
				Cells.isWithinRange(cell.y, this.minEdgeY(), this.maxEdgeY());
	}

	public Cell[] getAdjacentChunk() {
		return grid.getChunk(this.getAdjacentMin(), this.getAdjacentMax());
	}

	public Cell[][] getAdjacentChunk2D() {
		return grid.getChunk2D(this.getAdjacentMin(), this.getAdjacentMax());
	}
	
	// METHODS

	public boolean isSweepable() {
		return hidden && !marked;
	}

	public int sweep() {
		hidden = false;
		grid.sweptCount++;
		
		switch (value) {
			case DEF_VALUE:
				for (Cell cell : this.getAdjacentChunk())
					if (cell.isSweepable())
						cell.sweep();
			break;
			case MINE_VALUE:
				grid.revealAllMines();
		}

		return value;
	}
	
	/** 
	 * Returns true after reversing it's Marking status on the
	 * condition that the Cell is also Hidden.
	 */
	public boolean switchMarking() {
		if (hidden) {
			marked = !marked;
			return true;
		}

		return false;
	}
	
	/**
	 * Increments it's own Value to the total count of Cells 
	 * with a Value equal to mines.
	 */
	public void setupByAdjacentMines() {
		if (value != MINE_VALUE) {
			this.reset();
			for (Cell cell : this.getAdjacentChunk())
				value += cell.value == MINE_VALUE ? 1 : 0;
		}
	}

	public void reset() {
		value = DEF_VALUE;
		hidden = DEF_HIDDEN;
		marked = DEF_MARK;
	}
	
	@NonNull
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

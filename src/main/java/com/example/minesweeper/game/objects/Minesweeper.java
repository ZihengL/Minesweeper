package com.example.minesweeper.game.objects;

import com.example.minesweeper.game.enums.Difficulties;

public class Minesweeper {

	private GridManager manager = new GridManager();
	
	// CONSTRUCTORS

	public Minesweeper() {
		this(Difficulty.DEFAULT);
	}

	public Minesweeper(Difficulties difficulty) {
		this.setDifficulty(difficulty);
	}
	
	public Minesweeper(int rows, int columns) {
		this.setDifficulty(rows, columns);
	}

	// GETTERS
	
	public Cell get(int x, int y) {
		return this.manager.get(x, y);
	}
	
	public Cell[][] getGrid() {
		return this.manager.grid;
	}
	
	public GridManager getGridManager() {
		return this.manager;
	}

	// SETTERS
	
	public void setDifficulty(int rows, int columns) {
		Difficulty.set(Math.min(rows, columns), Math.max(rows, columns));
		manager.updateGrid();
	}
	
	public void setDifficulty(Difficulties difficulty) {
		Difficulty.set(difficulty);
		manager.updateGrid();
	}
	
	public void setupGame(int x, int y) {
		manager.setup(this.get(x, y));
	}

	// METHODS
	
	public boolean isWon() {
		return manager.area() - Difficulty.mines == manager.sweptCount;
	}
	
	public int sweepCell(int x, int y) {
		Cell cell = this.get(x, y);
		
		if (manager.sweptCount == 0)
			this.setupGame(x, y);
			
		return cell.isSweepable() ? cell.sweep() : Cell.ERR_VALUE;
	}

	public int sweepCell(Cell cell) {
		if (manager.sweptCount == 0)
			this.setupGame(cell.x, cell.y);

		return cell.isSweepable() ? cell.sweep() : Cell.ERR_VALUE;
	}

	public boolean mark(int x, int y) {
		return this.get(x, y).switchMarking();
	}
}


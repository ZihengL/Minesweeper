package com.example.minesweeper.game.objects;

import com.example.minesweeper.game.enums.Difficulties;

public class Difficulty {
	
	public static final Difficulties DEFAULT = Difficulties.BEGINNER;

	public static final int MAX = 30;
	public static final double MINES_RATIO = 0.15;
	
	protected static int rows = DEFAULT.rows;
	protected static int columns = DEFAULT.columns;
	protected static int mines = DEFAULT.mines;
	
	// GETTERS

	public static int getRows() {
		return Difficulty.rows;
	}

	public static int getColumns() {
		return Difficulty.columns;
	}

	public static int getMines() {
		return Difficulty.mines;
	}
	
	// SETTERS

	public static void setRows(int rows) {
		Difficulty.rows = Math.min(Math.max(rows, DEFAULT.rows), MAX);
	}

	public static void setColumns(int columns) {
		Difficulty.columns = Math.min(Math.max(columns, DEFAULT.columns), MAX);
	}

	public static void setMines(int mines) {
		Difficulty.mines = Math.max(mines, DEFAULT.mines);
	}

	public static void set(int rows, int columns) {
		Difficulty.setRows(rows);
		Difficulty.setColumns(columns);
		Difficulty.setMines(generateMinesAmount(Difficulty.rows, Difficulty.columns));
	}

	public static void set(Difficulties difficulty) {
		Difficulty.rows = difficulty.rows;
		Difficulty.columns = difficulty.columns;
		Difficulty.mines = difficulty.mines;
	}
	
	// METHODS

	public static int generateMinesAmount(int width, int height) {
		return (int) ((width * height) * MINES_RATIO);
	}
	
	// FIXIT
	public static int getLeftoverMinesForChunk(int width, int height, int leftover) {
		int original = Difficulty.generateMinesAmount(width, height);
		
		return Math.min(original, leftover);
	}

	public void setGridByDifficulty(GridManager grid) {
		grid.updateGrid();
	}
	
	public static String toMessage() {
		return "rows: " + rows + "\tcolumns: " + columns + "\tmines: " + mines;
	}
}

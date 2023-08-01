package com.example.minesweeper.game.enums;

import java.util.Locale;

public enum Difficulties {

	BEGINNER(9, 9, 10),
	INTERMEDIATE(16, 16, 40),
	EXPERT(30, 16, 99);
	
	public final int rows;
	public final int columns;
	public final int mines;
	
	private Difficulties(int rows, int columns, int mines) {
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
	}

	public String toString() {
		String name = this.name().charAt(0) + this.name().substring(1).toLowerCase();

		return name + "\n" + rows + " x " + columns + " (" + mines + " mines)";
	}
}
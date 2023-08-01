package com.example.minesweeper.game.others;

import com.example.minesweeper.game.objects.Cell;

public class Cells {

	public static boolean isWithinRange(int number, int min, int max) {
		return number >= min && number <= max;
	}
	
	public static int unitsBetween(int a, int b) {
		return a == b ? 0 : Math.abs(a - b) + 1;
	}
	
	public static int areaOf(Cell a, Cell b) {
		int width = unitsBetween(a.x, b.x), height = unitsBetween(a.y, b.x);
		
		return width == 0 || height == 0 ? Math.max(width, height) : width * height;
	}
	
	public static Cell minOf(Cell[][] chunk) {
		return chunk[0][0];
	}
	
	public static Cell maxOf(Cell[][] chunk) {
		return chunk[chunk.length - 1][chunk[0].length - 1];
	}
}

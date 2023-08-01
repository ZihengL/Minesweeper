package com.example.minesweeper.game.others;

public class Tools {
	
	/**
	 * Returns the largest multiple between half and an eight of the param value.
	 * If nothing is found, the param value is returned.
	 * 
	 * @param number An integer number.
	 * @return A multiple of the param value.
	 */
	public static int getMultipleOf(int number) {
		int start = 2, end = 8;
		
		while (start <= end) {
			if (number % start == 0)
				return start;
			
			start++;
		}
		
		return number;
	}
	
	public static boolean isWithinRange(int number, int min, int max) {
		return number >= min && number <= max;
	}
}

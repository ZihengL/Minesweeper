package com.example.minesweeper.game.objects;

import androidx.annotation.NonNull;

import com.example.minesweeper.game.others.Tools;

import java.util.Arrays;

public class GridManager {

    protected int sweptCount = 0;
    protected int rows = Difficulty.getRows();
    protected int columns = Difficulty.getColumns();
    protected Cell[][] grid = new Cell[rows][columns];

    // CONSTRUCTORS
    
    public GridManager() {
    	Cell.grid = this;
    }
    
    // GETTERS

	public int rows() {
    	return this.rows;
    }

	public int columns() {
    	return this.columns;
    }

	public int area() {
    	return this.rows * this.columns;
    }

	public Cell get(int x, int y) {
    	return this.isWithin(x, y) ? this.grid[x][y] : null;
    }

	public Cell[][] getGrid() {
    	return this.grid;
    }

	public int getSweptCount() {
        return this.sweptCount;
    }

	public Cell[] getGrid1D() {
		Cell[] grid = new Cell[this.area()];
		int index = 0;

		for (Cell[] row : this.grid)
			for (Cell cell : row) {
				grid[index] = cell;
				index++;
			}

		return grid;
	}

	public void flipGrid() {
		Cell temp = null;

		for (int x = 0; x < rows; x++)
			for (int y = 0; y < columns; y++) {
				temp = grid[x][y];
				grid[x][y] = grid[x][columns - y - 1];
				grid[x][columns - y - 1] = temp;
			}

		rows = Difficulty.rows = grid.length;
		columns = Difficulty.columns = grid[0].length;
	}

	public Cell[] getChunk(Cell start, Cell end) {
    	Cell[] chunk = new Cell[start.areaBetween(end)];
    	int index = 0;
    	
    	for (int x = start.x; x <= end.x; x++)
    		for (int y = start.y; y <= end.y; y++) {
    			chunk[index] = grid[x][y];
    			index++;
    		}
    	
    	return chunk;
    }

	public Cell[][] getChunk2D(Cell start, Cell end) {
    	Cell[][] chunk = new Cell[start.widthBetween(end)][start.heightBetween(end)];
    	
    	for (int x = start.x; x <= end.x; x++)
    		for (int y = start.y; y <= end.y; y++)
    			chunk[x - start.x][y - start.y] = grid[x][y];
    	
    	return chunk;
    }

	public Cell[] getChunkWithExclusion(Cell start, Cell end, Cell excluded) {
    	Cell[] chunk = new Cell[start.areaBetween(end)];
    	int index = 0;
    	
    	for (int x = start.x; x <= end.x; x++)
    		for (int y = start.y; y <= end.y; y++)
    			if (!grid[x][y].isAdjacentTo(excluded)) {
	    			chunk[index] = grid[x][y];
	    			index++;
    			}
    	
    	return Arrays.copyOf(chunk, index);
    }
    
    // SETTERS

	public void setSweptCount(int amountRevealed) {
        this.sweptCount = amountRevealed;
    }
    
    // VALIDATIONS 

	public boolean isWithin(int x, int y) {
        return this.isWithinRows(x) && this.isWithinColumns(y);
    }

	public boolean isWithinRows(int x) {
        return x >= 0 && x < rows;
    }

	public boolean isWithinColumns(int y) {
        return y >= 0 && y < columns;
    }
    
    // METHODS

	public void updateGrid() {
		this.sweptCount = 0;
    	this.rows = Difficulty.getRows();
    	this.columns = Difficulty.getColumns();
    	this.grid = new Cell[rows][columns];
    	
    	for (int x = 0; x < rows; x++)
    		for (int y = 0; y < columns; y++)
    			grid[x][y] = new Cell(x, y);
    }

	public void setup(Cell excluded) {
    	int chunkRows = rows / Tools.getMultipleOf(rows), chunkCols = columns / Tools.getMultipleOf(columns),
			leftover = Difficulty.mines;
    	Cell min = null, max = null;
    	
    	while (leftover > 0) {
			min = grid[0][0];
	    	for (int x = chunkRows - 1; x <= rows - 1; x += chunkRows) {
	    		for (int y = chunkCols - 1; y <= columns - 1; y += chunkCols) {
					max = grid[x][y];
    				leftover = this.setupChunk(this.getChunkWithExclusion(min, max, excluded), leftover);
	    			min = grid[min.x][Math.min(y + 1, columns - 1)];
	    		}
	    		min = grid[Math.min(x + 1, rows - 1)][0];
	    	}
    	}
    	
    	for (Cell[] row : grid)
    		for (Cell cell : row)
    			cell.setupByAdjacentMines();
    }

	public int setupChunk(Cell[] chunk, int leftover) {
    	int chunkMines = (int) ((double) chunk.length / this.area() * Difficulty.mines),
			randomIndex = 0;
    	
    	chunkMines = Math.min(chunkMines, leftover);
    	while (chunkMines > 0) {
    		randomIndex = (int) (Math.random() * chunk.length);
    		
    		if (chunk[randomIndex].value != Cell.MINE_VALUE) {
	    		chunk[randomIndex].value = Cell.MINE_VALUE;
	    		chunkMines--;
	    		leftover--;
    		}
    	}
    	
    	return leftover;
    }

	public void revealAllMines() {
        for (Cell[] row : grid)
            for (Cell cell : row)
                cell.hidden = cell.value != Cell.MINE_VALUE && cell.hidden;
    }

    @NonNull
	public String toString() {
    	StringBuilder message = new StringBuilder();
    	
    	for (Cell[] row : grid) {
    		for (Cell cell : row)
    			message.append(cell.value == Cell.MINE_VALUE ? "M " : cell.value + " ");
    	
    		message.append("\n");
    	}
    	
    	return message.toString();
    }
}


package dataHandler;

import java.util.Random;

public class Grid {
	public Cell[][] grid;
	private int gridSize;
	
	public Grid(int gridSize, boolean random) {
		Cell[][] newGrid = new Cell[gridSize][gridSize];
		this.gridSize = gridSize;
		this.grid = newGrid;
		this.fillGrid();
		if (random) this.randomizeGrid();
	}
	
	
	private void fillGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				this.grid[i][j] = new Cell(i, j, false);
			}
		}
	}
	
	public Cell[][] getGrid() {
		return this.grid;
	}
	
	public void importGrid(Cell[][] newGrid) {
		this.grid = newGrid;
		this.gridSize = this.grid.length;
	}
	
	public void updateGrid() {
		Grid nextGrid = new Grid(this.gridSize, false);
		
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				nextGrid.getGrid()[i][j].updateCell(this.checkCell(this.grid[i][j]));
			}
		}
		this.grid = nextGrid.getGrid();
	}
	
	public void randomizeGrid() {
		Random randomGen = new Random();
	
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				this.grid[i][j].updateCell(randomGen.nextBoolean()); 
			}
		}
	}
	
	
	private boolean checkCell(Cell cell) {
			int aliveCounter = this.countAliveNeighbours(cell);

			if (cell.getStatus() && aliveCounter < 2) return false;
			else if (cell.getStatus() && (aliveCounter == 2 || aliveCounter == 3)) return true;
			else if (cell.getStatus() && aliveCounter > 3) return false;
			else if (!cell.getStatus() && aliveCounter == 3) return true;
			else return false;
	}
	
	private int countAliveNeighbours(Cell cell) {
		int currentX = cell.getPosition()[0];
		int currentY = cell.getPosition()[1];
		int aliveCounter = 0;
	
		for (int i = -1; i <= 1; i++) {
			   for (int j = -1; j <= 1; j++) {
				   if (i == 0 && j == 0) continue;
				   if ((currentX + i) < 0 || (currentX + i) >= this.gridSize || (currentY + j) < 0 || (currentY + j) >= this.gridSize) continue;
				   if (grid[currentX + i][currentY + j].getStatus()) aliveCounter++;				
			   }
		}
		
		return aliveCounter;
	}
	
	public void printGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				System.out.println(grid[i][j].toString());
			}
		}
	}
}

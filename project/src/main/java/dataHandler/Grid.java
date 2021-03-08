package dataHandler;

public class Grid {
	private Cell[][] grid;
	
	public Grid(int width, int height) {
		Cell[][] newGrid = new Cell[width][height];
		
		this.grid = newGrid;
		this.fillGrid();
	}
	
	
	private void fillGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				this.grid[i][j] = new Cell(i, j, false);
			}
		}
	}
	
	public Cell[][] getGrid() {
		return grid;
	}

}

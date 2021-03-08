package dataHandler;

public class Grid {
	private Cell[][] grid;
	
	
	 public static void main(String[] args) {
		 Grid myGrid = new Grid(30, 30);
		 myGrid.printGrid();
		 
		 //myGrid.checkGrid();
		 //myGrid.printGrid();

	 }
	
	public Grid(int width, int height) {
		Cell[][] newGrid = new Cell[width][height];
		
		this.grid = newGrid;
		this.fillGrid();
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
	
	private void checkGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				this.grid[i][j].updateCell(this.checkCell(this.grid[i][j])); 
			}
		}
	}
	
	private boolean checkCell(Cell cell) {
		return true;
	}
	
	
	public void printGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				System.out.println(grid[i][j].toString());
			}
		}
	}
}

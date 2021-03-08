package dataHandler;

public class Grid {
	public Cell[][] grid;
	
	
	 public static void main(String[] args) {
		 Grid myGrid = new Grid(10, 10);
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
	
	private void updateGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				this.grid[i][j].updateCell(this.checkCell(this.grid[i][j])); 
			}
		}
	}
	
	@SuppressWarnings("finally")
	private boolean checkCell(Cell cell) {
		
		int currentX = cell.getPosition()[0];
		int currentY = cell.getPosition()[1];
		int aliveCounter = 0;
		try {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (grid[currentX + i][currentY + j].getStatus()) aliveCounter++;
				}
			}
		} finally {
			if (cell.getStatus() && aliveCounter < 2) return false;
			else if (cell.getStatus() && (aliveCounter == 2 || aliveCounter == 3)) return true;
			else if (cell.getStatus() && aliveCounter > 3) return false;
			else if (!cell.getStatus() && aliveCounter == 3) return true;
			else return false;
		}
	}
	
	
	public void printGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				System.out.println(grid[i][j].toString());
			}
		}
	}
}

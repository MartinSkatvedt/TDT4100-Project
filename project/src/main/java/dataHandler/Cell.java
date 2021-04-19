package dataHandler;

public class Cell {
	private int[] position;
	private boolean active;
	
	public Cell(int x, int y, boolean alive) {
		if (x < 0 || y < 0) throw new IllegalArgumentException("Cell position cant be negative");
		
		int[] newPosition = {x,y};
		this.position = newPosition;
		this.active = alive;
	}
	
	public int[] getPosition() {
		return this.position;
	}
	
	public boolean getStatus() {
		return this.active;
	}
	
	public void updateCell(boolean newState) {
		this.active = newState;
	}
}

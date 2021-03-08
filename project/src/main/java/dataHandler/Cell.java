package dataHandler;

public class Cell {
	private int[] position;
	private boolean active;
	
	public Cell(int x, int y, boolean alive) {
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
}

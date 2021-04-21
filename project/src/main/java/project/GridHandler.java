package project;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridHandler {
	private int gridSize = 50;
	private double canvasSize = 500;
	private double resolution;
	private Grid grid;
	private AppController appController;
	
	public GridHandler(AppController appController) {
		this.grid = new Grid(this.gridSize, false);		
		this.resolution = this.canvasSize/this.gridSize;
		this.appController = appController;
	}
	
	public void setNewgridSize(int newGridSize) {
		this.gridSize = newGridSize;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
	
	public void randomizeGrid() {
		this.grid.randomizeGrid();
		this.drawGrid();
	}
	
	public void loadNewGrid(Cell[][] newGrid) {
		this.grid.importGrid(newGrid);
		this.setNewgridSize(newGrid.length);
		this.resolution = this.canvasSize/this.gridSize;
		this.drawGrid();
	}
	
	public void drawGrid() {
		if (this.appController == null) return; // This is for the test
		Canvas canvas = this.appController.getCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvasSize, this.canvasSize);
   
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
        		gc.setFill(this.grid.getGrid()[i][j].getStatus() ? Color.WHITE : Color.BLACK);
        		gc.setStroke(Color.GRAY);
        		gc.setLineWidth(1);
        		
                gc.strokeRect(0.5 + i * this.resolution, 0.5 + j * this.resolution, this.resolution - 1, this.resolution - 1);
        		gc.fillRect(0.5 + i * this.resolution, 0.5 + j * this.resolution, this.resolution - 1, this.resolution - 1);
            }
          }        
    }
	
	public void updateCanvas() {
		this.grid.updateGrid();
		this.drawGrid();
	}
	
	public void resetGrid() {
		this.grid = new Grid(this.gridSize, false);
		this.resolution = this.canvasSize/this.gridSize;
		this.drawGrid();
	}
	
	public void drawOnGrid(double pressX, double pressY) {
		int x = (int)(pressX/this.resolution);
		int y = (int)(pressY/this.resolution);
		
		if (x < 0 || y < 0 || x >= this.gridSize || y >= this.gridSize) return;
		
		this.grid.getGrid()[x][y].updateCell(true);
		this.drawGrid();
	}
}

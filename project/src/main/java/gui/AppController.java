package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import dataHandler.Cell;
import dataHandler.Grid;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AppController {
	private int gridSize = 50;
	double canvasSize;
	double animationSpeed = 0.1;
	private double resolution;
	
	Timeline timeline;
	Grid grid;
	
	@FXML private Canvas canvas;
	@FXML private Button playButton;
	@FXML private Button slowButton;
	@FXML private Button normalButton;
	@FXML private Button fastButton;
	@FXML private Button smallGridButton;
	@FXML private Button mediumGridButton;
	@FXML private Button bigGridButton;

	public AppController() {
		this.grid = new Grid(this.gridSize, false);
		
	    this.timeline = new Timeline(new KeyFrame(Duration.seconds(this.animationSpeed), e -> this.nextFrame()));
	    this.timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	@FXML
	private void initialize() {
		this.canvasSize = this.canvas.getHeight();
		this.resolution = canvasSize/this.gridSize;
		this.fillGrid();
		
		this.canvas.setOnMouseDragged((MouseEvent event) -> {
	        this.handleMouseEvent(event);
	    });
		
		this.canvas.setOnMouseClicked((MouseEvent event) -> {
			this.handleMouseEvent(event);
	    });
		
	}
	
	public void loadGrid() {
		
	}
	
	public void saveGrid() throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter("myfile.csv"));
		StringBuilder sb = new StringBuilder();
		for (Cell[] row : this.grid.getGrid()) {
			for (Cell cell : row) {
				sb.append(cell);
				sb.append(",");
			}
			br.write(sb.toString());

		}

		br.close();
		
		System.out.println("Saved");
	}
	
	public void clearCanvas() {
		if (this.timeline.getStatus() == Status.RUNNING) this.startSimulation();
		this.grid = new Grid(this.gridSize, false);
		this.canvasSize = this.canvas.getHeight();
		this.resolution = canvasSize/this.gridSize;
		this.fillGrid();
	}
	
	public void startSimulation() {
		if (this.timeline.getStatus() == Status.RUNNING) {
			this.timeline.stop();
			playButton.setText("Start");
		}
		else {
			this.timeline.play();
			playButton.setText("Stop");
		}
	}
	
	public void changeGridSize(ActionEvent event) {
		int newGridSize = 25;
		Button button = (Button) event.getSource();
		switch (button.getText()) {
		case "50x50":
			newGridSize = 50;
			break;
		case "25x25":
			newGridSize = 25;
			break;
		case "10x10":
			newGridSize = 10;
			break;
		default:
			break;
		}
		
		this.gridSize = newGridSize;
		this.clearCanvas();
	}
	
	
	public void handleMouseEvent(MouseEvent event) {
		double pressX = event.getX();
		double pressY = event.getY();
		
		int x = (int)(pressX/this.resolution);
		int y = (int)(pressY/this.resolution);
		
		if (this.timeline.getStatus() == Status.STOPPED) {
			this.grid.getGrid()[x][y].updateCell(true);
			fillGrid();
		}
	}
	
	
	public void changeSpeed(ActionEvent event) {
		Button button = (Button) event.getSource();
		switch (button.getText()) {
		case "Fast":
			this.animationSpeed = 0.05;
			break;
		case "Normal":
			this.animationSpeed = 0.1;
			break;
		case "Slow":
			this.animationSpeed = 0.5;
			break;
		default:
			this.animationSpeed = 0.1;
			break;
		}
		
		if (this.timeline.getStatus() == Status.RUNNING) this.startSimulation();
	    this.timeline = new Timeline(new KeyFrame(Duration.seconds(this.animationSpeed), e -> this.nextFrame()));
	    this.timeline.setCycleCount(Timeline.INDEFINITE);
		
	    this.startSimulation();
	}
	
	private void nextFrame() {
		 this.grid.updateGrid();
		 this.fillGrid();
	}
	
	private void fillGrid() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
   
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
	
	
}

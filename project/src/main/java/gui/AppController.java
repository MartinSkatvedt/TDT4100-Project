package gui;

import dataHandler.Grid;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AppController {
	private int gridSize = 50;
	double canvasSize;
	double animationSpeed = 0.5;
	
	Timeline timeline;
	Grid grid;
	
	@FXML private Canvas canvas;
	@FXML private Button playButton;
	@FXML private Button slowButton;
	@FXML private Button normalButton;
	@FXML private Button fastButton;

	public AppController() {
		this.grid = new Grid(this.gridSize, true);
		
	    this.timeline = new Timeline(new KeyFrame(Duration.seconds(this.animationSpeed), e -> this.nextFrame()));
	    this.timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	@FXML
	private void initialize() {
		this.canvasSize = this.canvas.getHeight();
		this.fillGrid();
	}
	
	@FXML
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
	
	
	public void changeSpeed(ActionEvent event) {
		Button button = (Button) event.getSource();
		switch (button.getText()) {
		case "Fast":
			this.animationSpeed = 0.05;
			break;
		case "Normal":
			this.animationSpeed = 0.5;
			break;
		case "Slow":
			this.animationSpeed = 1.0;
			break;
		default:
			this.animationSpeed = 1.0;
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
        
        double res = canvasSize/this.gridSize;

        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
        		gc.setFill(this.grid.getGrid()[i][j].getStatus() ? Color.WHITE : Color.BLACK);
        		gc.setStroke(Color.GRAY);
        		gc.setLineWidth(1);
        		
                gc.strokeRect(0.5 + i * res, 0.5 + j * res, res - 1, res - 1);
        		gc.fillRect(0.5 + i * res, 0.5 + j * res, res - 1, res - 1);

            }
          }
    }
	
	
}

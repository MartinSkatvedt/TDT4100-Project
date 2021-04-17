package gui;

import java.io.IOException;

import dataHandler.CSVLoader;
import dataHandler.GridHandler;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class AppController {	
	private CSVLoader fileLoader;
	private GridHandler gridHandler;
	private Timeline timeline;
	private double animationSpeed = 0.1;
    ObservableList<String> templateList = FXCollections.observableArrayList("Glider", "Glidergun");

		
	@FXML private Canvas canvas;
	@FXML private Button playButton;
	@FXML private Button slowButton;
	@FXML private Button normalButton;
	@FXML private Button fastButton;
	@FXML private Button smallGridButton;
	@FXML private Button mediumGridButton;
	@FXML private Button bigGridButton;
	@FXML private ChoiceBox<String> templateSetter;
	
	public AppController() {
		this.gridHandler = new GridHandler(this);
		this.timeline = new Timeline(new KeyFrame(Duration.seconds(this.animationSpeed), e -> this.nextFrame()));
		this.timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	@FXML
	private void initialize() {
		this.gridHandler.drawGrid();		

		this.canvas.setOnMouseDragged((MouseEvent event) -> {
	        this.handleMouseEvent(event);
	    });
		
		this.canvas.setOnMouseClicked((MouseEvent event) -> {
			this.handleMouseEvent(event);
	    });
		
		this.templateSetter.setValue("Custom");
		this.templateSetter.setItems(templateList);

	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public void loadGrid() throws IOException {
	    this.fileLoader = new CSVLoader( this.canvas.getScene().getWindow());	
		if (this.timeline.getStatus() == Status.RUNNING) this.startSimulation();
		this.gridHandler.loadNewGrid(fileLoader.loadGridFromFile());
	}
	
	
	public void saveGrid() throws IOException {
	    this.fileLoader = new CSVLoader( this.canvas.getScene().getWindow());		
		this.fileLoader.saveGridToFile(this.gridHandler.getGrid());
	}
	
	public void clearCanvas() {
		if (this.timeline.getStatus() == Status.RUNNING) this.startSimulation();
		this.gridHandler.resetGrid();
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
		this.gridHandler.setNewgridSize(newGridSize);
		this.clearCanvas();
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
		 this.gridHandler.updateCanvas();
	}
	

	public void handleMouseEvent(MouseEvent event) {
		double pressX = event.getX();
		double pressY = event.getY();
		
		if (this.timeline.getStatus() == Status.STOPPED) {
			this.gridHandler.drawOnGrid(pressX, pressY);
		}
	}
	

}

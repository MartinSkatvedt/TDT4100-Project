package gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AppController {
	private int columns = 10;
	private int height = 10;
	
	
	@FXML private Canvas canvas;
	
	@FXML
	public void initialize() {
		GraphicsContext graphics_context = this.canvas.getGraphicsContext2D();
		 graphics_context.setFill(Color.BLACK);
	     graphics_context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	     
	     for (int i = 0; i < this.columns; i++) {
	    	 
	     }
	     
	     
	     
	}
	
	@FXML
	public void startSimulation() {
		GraphicsContext graphics_context = this.canvas.getGraphicsContext2D();
		 graphics_context.setFill(Color.RED);
	     graphics_context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	     
	}
	
	
	private Canvas createCanvasGrid(int width, int height, boolean sharp) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        for (double x = sharp ? 0.5 : 0.0; x < width; x+=10) {
            gc.moveTo(x, 0);
            gc.lineTo(x, height);
            gc.stroke();
        }
        
        for (double y = sharp ? 0.5 : 0.0; y < height; y+=10) {
            gc.moveTo(0, y);
            gc.lineTo(width, y);
            gc.stroke();
        }
        return canvas ;
    }
	
}

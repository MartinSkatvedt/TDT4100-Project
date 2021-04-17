package dataHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import interfaces.FileLoader;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class CSVLoader implements FileLoader {
	private Window window;
	private String templatePath;
	
	public CSVLoader(Window window) {
		this.window = window;
		this.templatePath = Paths.get(".").toAbsolutePath().normalize().toString() + "/templates";
	}
	
	public Cell[][] loadGridFromFileChooser() throws IOException {
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setInitialDirectory(new File(this.templatePath));
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("CSV", "*.csv")
			);
		
		File selectedFile = fileChooser.showOpenDialog(this.window);
				 
		 List<String[]> content = new ArrayList<String[]>();
		
		    try(BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		        String line = "";
		        while ((line = br.readLine()) != null) {
		        	String[] stringLine = line.split(",");
		        	content.add(stringLine);
		        }
		    } catch (FileNotFoundException e) {
		      //Some error logging
		    }
		    
		    Cell[][] grid = this.stringArrayToGrid(content);
		    return grid;
	}
	
	public Cell[][] loadGridFromFile(String string) throws IOException {				 
		 List<String[]> content = new ArrayList<String[]>();
		
		    try(BufferedReader br = new BufferedReader(new FileReader(string))) {
		        String line = "";
		        while ((line = br.readLine()) != null) {
		        	String[] stringLine = line.split(",");
		        	content.add(stringLine);
		        }
		    } catch (FileNotFoundException e) {
		      //Some error logging
		    }
		    
		    Cell[][] grid = this.stringArrayToGrid(content);
		    return grid;
	}
	
	
	private Cell[][] stringArrayToGrid(List<String[]> stringArray) {
		int gridSize = stringArray.size();
		
		Cell[][] newGrid = new Cell[gridSize][gridSize];

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				newGrid[i][j] = new Cell(i, j, Boolean.parseBoolean(stringArray.get(i)[j]));
			}
		}
		return newGrid;
	}
	
	
	
	public void saveGridToFile(Grid grid) throws IOException {
		FileChooser fileChooser = new FileChooser();

		
		fileChooser.setInitialDirectory(new File(this.templatePath));
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("CSV", "*.csv")
			);
		
		File selectedFile = fileChooser.showSaveDialog(this.window);
		
		BufferedWriter br = new BufferedWriter(new FileWriter(selectedFile));
		
		for (Cell[] row : grid.getGrid()) {
			for (Cell cell : row) {
				br.append(String.valueOf(cell.getStatus()));
				br.append(",");
			}
			br.append("\n");
		}

		br.close();
	}	
	
}
package project;
import java.io.IOException;

public class Template {
	private Cell[][] cellGrid;
	
	public Template(String fileName) throws IOException {
		CSVLoader fileLoader = new CSVLoader( null);
		this.cellGrid = fileLoader.loadGridFromFile(fileName);
	}
	
	public Cell[][] getGrid() {
		return this.cellGrid;
	}
}
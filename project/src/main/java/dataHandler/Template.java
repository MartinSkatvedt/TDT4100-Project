package dataHandler;
import java.io.IOException;

public class Template {
	private Cell[][] cellGrid;
	
	public Template(String string) throws IOException {
		CSVLoader fileLoader = new CSVLoader( null);
		this.cellGrid = fileLoader.loadGridFromFile(string);

	}
	
	public Cell[][] getGrid() {
		return this.cellGrid;
	}
}

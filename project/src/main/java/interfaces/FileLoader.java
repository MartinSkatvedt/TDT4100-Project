package interfaces;

import java.io.IOException;

import dataHandler.Cell;
import dataHandler.Grid;

public interface FileLoader {
	public Cell[][] loadGridFromFile() throws IOException;
	public void saveGridToFile(Grid grid) throws IOException;
}

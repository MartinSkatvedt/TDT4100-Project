package project;

import java.io.IOException;

public interface FileLoader {
	public Cell[][] loadGridFromFileChooser() throws IOException;
	public void saveGridToFile(Grid grid) throws IOException;
}

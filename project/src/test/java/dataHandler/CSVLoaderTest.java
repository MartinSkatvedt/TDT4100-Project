package dataHandler;

import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVLoaderTest {
	
	@Test
	@DisplayName("Load file test")
	void loadFile() {
		CSVLoader loader = new CSVLoader(null);
		assertThrows(IOException.class, () -> {
			loader.loadGridFromFile("####");
		});	
	}
	
}

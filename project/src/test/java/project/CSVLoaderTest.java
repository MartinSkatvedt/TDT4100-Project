package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CSVLoaderTest {
	
	@Test
	@DisplayName("Load file test")
	void loadFile() throws IOException {
		CSVLoader loader = new CSVLoader(null);
		Cell[][] gridFromLoader = loader.loadGridFromFile("./testTemplates/test.csv");
		
		assertAll("checks grid",
				() -> assertTrue(gridFromLoader[0][0].getStatus()),
				() -> assertFalse(gridFromLoader[1][0].getStatus()),
				() -> assertFalse(gridFromLoader[2][0].getStatus()),
				() -> assertFalse(gridFromLoader[0][1].getStatus()),
				() -> assertTrue(gridFromLoader[1][1].getStatus()),
				() -> assertFalse(gridFromLoader[2][1].getStatus()),
				() -> assertFalse(gridFromLoader[0][2].getStatus()),
				() -> assertFalse(gridFromLoader[1][2].getStatus()),
				() -> assertTrue(gridFromLoader[2][2].getStatus())
		);
	}
	
	@Test
	@DisplayName("Tests to see what happens when we load a not existent file")
	void noFileTest() throws IOException {
		CSVLoader loader = new CSVLoader(null);
		Cell[][] gridFromLoader = loader.loadGridFromFile("###");
		
		assertAll("Checks if grid is all false",
				() -> {
					for (Cell[] row: gridFromLoader) {
						for (Cell currentCell: row) {
							assertFalse(currentCell.getStatus());
						}
					}
				});
		
		assertEquals(25, gridFromLoader.length);
	}
	
}


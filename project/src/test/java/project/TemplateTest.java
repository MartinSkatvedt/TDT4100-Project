package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TemplateTest {
	
	@Test
	@DisplayName("Tests the Template Class")
	void getterTest() throws IOException {
		Template newTemplate = new Template("./testTemplates/test.csv");
		assertTrue(newTemplate.getGrid() instanceof Cell[][]);
		
		assertAll("checks grid",
				() -> assertTrue(newTemplate.getGrid()[0][0].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[1][0].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[2][0].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[0][1].getStatus()),
				() -> assertTrue(newTemplate.getGrid()[1][1].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[2][1].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[0][2].getStatus()),
				() -> assertFalse(newTemplate.getGrid()[1][2].getStatus()),
				() -> assertTrue(newTemplate.getGrid()[2][2].getStatus())
		);
	}
	
	@Test
	@DisplayName("Tests to see what happens when we load a not existent file")
	void noFileTest() throws IOException {
		Template newTemplate = new Template("###");
		
		assertAll("Checks if grid is all false",
				() -> {
					for (Cell[] row: newTemplate.getGrid()) {
						for (Cell currentCell: row) {
							assertFalse(currentCell.getStatus());
						}
					}
				});
		
		assertEquals(25, newTemplate.getGrid().length);
	}
}

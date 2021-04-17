package dataHandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CellTest {
	@Test
    @DisplayName("Testing Cell update method")
	void statusTest() {
		Cell cell = new Cell(0,0,false);
		assertFalse(cell.getStatus());
		cell.updateCell(true);
		assertTrue(cell.getStatus());
	}
	
	
	@Test
	@DisplayName("Constructor arg test")
	void constructorTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Cell cell = new Cell(-1, 15, false);
		});
	}
}

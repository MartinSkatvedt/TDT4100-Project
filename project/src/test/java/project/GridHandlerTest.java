package project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class GridHandlerTest {
	private GridHandler gridHandler;
	
	@BeforeEach
	void setupTest() {
		this.gridHandler = new GridHandler(null);
	}
	
	@Test
	@DisplayName("Reset grid test")
	void resetGridTest() {
		this.gridHandler.randomizeGrid();
		assertFalse(this.checkEachCell(false));
		this.gridHandler.resetGrid();
		assertTrue(this.checkEachCell(false));
	}
	
	@Test
	@DisplayName("Draw on grid test")
	void drawTest() {
		for (double x = 0.0; x <= 500; x += 1) {
			for (double y = 0.0; y <= 500; y += 1) {
				this.gridHandler.drawOnGrid(x, y);
			}
		}
		assertTrue(this.checkEachCell(true));
	}
	
	
	private boolean checkEachCell(boolean checkState) {
		for (Cell[] row: this.gridHandler.getGrid().getGrid()) {
			for (Cell currentCell : row) {
				if (currentCell.getStatus() == checkState) continue;
				else return false;
			}
		}
		return true;
	}
}


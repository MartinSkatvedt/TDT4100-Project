package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project.Cell;
import project.Grid;
	
public class GridTest {
		
	@Test
	@DisplayName("Constructor arg test")
	void constructorTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Grid(-1, false);
		});	
	}

	
	@Test 
	@DisplayName("Correct grid creation test") 
	void gridTest() {
		Grid grid = new Grid(5, false);
		
		assertAll("Gridsize",
				() -> assertEquals(5, grid.getGrid().length),
				() -> {
					for (Cell[] column: grid.getGrid()) {
						assertEquals(5,column.length);
					}
				}
		);
		
		assertAll("Cells",
				() -> {
					for (Cell[] column: grid.getGrid()) {
						for (Cell cell: column) {
							assertFalse(cell.getStatus());
						}
					}
				});
		
		Grid grid2 = new Grid(20, false);
		
		assertAll("Gridsize",
				() -> assertEquals(20, grid2.getGrid().length),
				() -> {
					for (Cell[] column: grid2.getGrid()) {
						assertEquals(20,column.length);
					}
				}
		);
		
		assertAll("Cells",
				() -> {
					for (Cell[] column: grid2.getGrid()) {
						for (Cell cell: column) {
							assertFalse(cell.getStatus());
						}
					}
				});
	}
	
	@Test
	@DisplayName("Correct neighbour count test")
	void neighbourstest() {
		Grid grid = new Grid(5, false);
		
		grid.getGrid()[0][0].updateCell(true);
		grid.getGrid()[1][0].updateCell(true);
		grid.getGrid()[2][1].updateCell(true);
		
		assertAll("Cells", 
				() -> assertTrue(grid.checkCell(grid.getGrid()[1][1])),
				() -> assertTrue(grid.checkCell(grid.getGrid()[1][0])),
				() -> assertFalse(grid.checkCell(grid.getGrid()[0][0]))
		);
	}
	
	@Test
	@DisplayName("Correct grid update test")
	void updateTest() {
		Grid grid1 = new Grid(5, false);
		Grid grid2 = new Grid(5, false);

		grid1.getGrid()[0][0].updateCell(true);
		grid1.getGrid()[1][0].updateCell(true);
		grid1.getGrid()[2][1].updateCell(true);
		 
		grid1.updateGrid();
		
		grid2.getGrid()[1][0].updateCell(true);
		grid2.getGrid()[1][1].updateCell(true);

		
		assertAll("Compare grids",
				() -> {
					for (int i = 0; i < 5; i ++) {
						for (int j = 0; j < 5; j++) {
							assertEquals(grid1.getGrid()[i][j].getStatus(), 
									grid2.getGrid()[i][j].getStatus());
						}
					}
				});
	}
}

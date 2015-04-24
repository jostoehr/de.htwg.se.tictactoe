package de.htwg.tictactoe.entities;
/**
 *
 * @author johannes
 */
public class Grid {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    
    private Cell[][] cells;
    
    public Grid() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col); 
            }
        }
    }
    
    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].clear();  // clear the cell content
            }
        }
    }
    
    public Cell getCell(int row, int column) {
	return cells[row][column];
    }
    
    	
    public void setCell(int row, int column, char value) {
	cells[row][column].setValue(value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                sb.append(cells[row][col].getValue()).append(" ");   
                if (col < COLS - 1) 
                    sb.append("|");
            }
            sb.append("\n");
            if (row < ROWS - 1) {
                sb.append("----------\n");
            }
        }
        return sb.toString();
    }
    
    
}

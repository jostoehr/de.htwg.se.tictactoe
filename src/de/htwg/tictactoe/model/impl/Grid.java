package de.htwg.tictactoe.model.impl;

import de.htwg.tictactoe.util.Value;
import de.htwg.tictactoe.model.ICell;
import de.htwg.tictactoe.model.IGrid;

/**
 * Model Class to save a grid field with his Cells using Matrix.
 * @author Johannes Stöhr
 */
public class Grid implements IGrid {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    
    private final ICell[][] cells;
    
    public Grid() {
        cells = new ICell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col); 
            }
        }
    }
    
    @Override
    public ICell getCell(int row, int column) {
	return cells[row][column];
    }
    
    @Override	
    public void setCell(int row, int column, Value value) {
	cells[row][column].setValue(value);
    }
       
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) { 
                sb.append(" ").append(cells[row][col].getValue()).append(" ");   
                if (col < COLS - 1){
                    sb.append("|");
                } 
            }
            sb.append("\n");
            if (row < ROWS - 1) {
                sb.append("-----------\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}

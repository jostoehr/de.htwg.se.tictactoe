package de.htwg.tictactoe.model.impl;

import de.htwg.tictactoe.util.Value;
import de.htwg.tictactoe.model.ICell;

/**
 * Model Class to save a cell with a Value.
 * @author Johannes Stöhr
 */
public class Cell implements ICell {
    private final int row;
    private final int column;
    private Value value;
    
    public Cell(int row, int column) {
        this.row=row;
        this.column=column;
        clear();
    }

    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public Value getValue() {
        return value;
    }
    
    @Override
    public int getRow() {
        return row;
    }
    
    @Override
    public int getColumn() {
        return column;
    }
    
    @Override
    public boolean isSet() {
        return value != Value.EMPTY;
    }
    
    @Override
    public boolean isUnSet() {
        return !isSet();
    }
    
    @Override
    public String toString() {
	return "(" + row + "," + column + ") = " + value;
    }
    
    @Override
    public final void clear() {
        value = Value.EMPTY;
    }
}

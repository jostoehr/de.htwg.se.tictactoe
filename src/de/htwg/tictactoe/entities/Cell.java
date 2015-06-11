package de.htwg.tictactoe.entities;

/**
 *
 * @author Johannes Stöhr
 */
public class Cell {
    private int row;
    private int column;
    private Value value;
    
    public Cell(int row, int column) {
        this.row=row;
        this.column=column;
        clear();
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Enum getValue() {
        return value;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isSet() {
        return value != Value.EMPTY;
    }
    
    public boolean isUnSet() {
        return !isSet();
    }
    
    /**
     * returns a String of the form "(0,0) = x"
    */
    @Override
    public String toString() {
	return "(" + row + "," + column + ") = " + value;
    }
    
    public final void clear() {
        value = Value.EMPTY;
    }
}

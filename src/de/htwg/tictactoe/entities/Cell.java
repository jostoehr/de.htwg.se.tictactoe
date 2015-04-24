package de.htwg.tictactoe.entities;

public class Cell {
    private int row;
    private int column;
    private char value;

    public Cell(int row, int column) {
        this.row=row;
        this.column=column;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isSet() {
        return value != ' ';
    }
    
    public boolean isUnSet() {
        return !isSet();
    }
    
    /**
     * returns a String of the form "(0,0) = x"
    */
    public String mkString() {
	return "(" + row + "," + column + ") = " + value;
    }
    
    public void clear() {
        value = ' ';
    }
}

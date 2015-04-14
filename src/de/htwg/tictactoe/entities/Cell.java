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
}

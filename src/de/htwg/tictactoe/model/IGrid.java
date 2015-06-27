package de.htwg.tictactoe.model;

import de.htwg.tictactoe.util.Value;

/**
 * IGrid is an Utitlity-Interface.
 * @author Johannes Stöhr
 */
public interface IGrid {
    
    /**
     * Getter for the cell of Matrix cells.
     * @param row row of the cell
     * @param column column of the cell
     * @return Cell in Matrix
     */
    ICell getCell(int row, int column);
    
    /**
     * Setter for the cell of Matrix cells.
     * @param row row of the cell
     * @param column column of the cell
     * @param value character of the cell
     */
    void setCell(int row, int column, Value value);
    
    /**
     * Method to get a grid field as String using StringBuilder.
     * @return grid as String
     */
    String toString();
}

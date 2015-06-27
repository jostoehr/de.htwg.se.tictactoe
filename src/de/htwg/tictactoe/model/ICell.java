package de.htwg.tictactoe.model;

import de.htwg.tictactoe.util.Value;

/**
 * ICell is an Utitlity-Interface. 
 * @author Johannes Stöhr
 */
public interface ICell {
    
    /**
     * Setter for the Value of a Cell.
     * @param value character of Enum value
     */
    void setValue(Value value);
    
    /**
     * Getter for the Value of a Cell.
     * @return value of cell
     */
    Value getValue();
    
    /**
     * Getter for the Row of a cell.
     * @return row of cell
     */
    int getRow();
    
    /**
     * Getter for the Column of a cell.
     * @return column of cell
     */
    int getColumn();
    
    /**
     * Method to check if a cell is set.
     * @return true if set
     */
    boolean isSet();
    
    /**
     * Method to check if a cell is unset;
     * @return true if unset
     */
    boolean isUnSet();
    
    /**
     * Method to get a Cell in String format like 
     * "(0,0) = x".
     * @return Cell in String
     */
    String toString();
    
    /**
     * Method to set a cell to Value.EMPTY.
     */
    void clear();
}

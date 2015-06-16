package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.util.State;

/**
 * IMasterController is an Utitlity-Inferace
 * for different Controllers
 * @author Johannes Stöhr
 */
public interface IMasterController {
    
    /**
     * Method to set a Cell in grid.
     * @param row row of matrix
     * @param column column of matrix
     */
    public void setValue(int row, int column);
    
    /**
     * Method to get the current status message of setValue.
     * @return current status Message
     */
    public String getStatus();
    
    /**
     * Getter for the current grid to get it as String.
     * Grid.toString() builds the playing field for the TUI.
     * @return toString() of current grid
     */
    public String getGridString();
    
    /**
     * Setter for the current State of State Pattern.
     * @param currentState new State
     */
    public void setCurrentState(State currentState);

}

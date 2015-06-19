package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.entities.Player;
import de.htwg.tictactoe.entities.Value;
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

    /**
     * Getter for the current State of State Pattern.
     * @return current State
     */
    public State getCurrentState();
    
    /**
     * Getter for Player 1. 
     * @return player1
     */
    public Player getPlayer1();
    
    /**
     * Getter for Player 2.
     * @return player2
     */
    public Player getPlayer2();
    
    /**
     * Setter for Player 1.
     * @param name name of Player 1
     * @param character playing character of Player 1
     */
    public void setPlayer1(String name, Value character);
    
    /**
     * Setter for Player 2.
     * @param name name of Player 2
     * @param character playing character of Player 2
     */
    public void setPlayer2(String name, Value character);
    
    /**
     * Getter for the current Player.
     * @return current Player or null if no one is set
     */
    public Player getCurrentPlayer();

    /**
     * Method to initialize the current grid with Empty Cells.
     */
    public void init();
    
    /**
     * Method to 
     * @return 
     */
    public boolean isDraw();
    
}

package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.model.IPlayer;
import de.htwg.tictactoe.util.Value;
import de.htwg.tictactoe.util.State;
import java.util.List;

/**
 * IMasterController is an Utitlity-Inferace
 * for different Controllers
 * @author Johannes Stöhr
 */
public interface IMasterController {
    
    /**
     * Setter for a Value in current grid.
     * @param row row of matrix
     * @param column column of matrix
     */
    void setValue(int row, int column);
    
    /**
     * Getter for the current status Message of setValue.
     * @return current status Message
     */
    String getStatus();
    
    /**
     * Getter for the current grid to get it as String.
     * Grid.toString() builds the playing field for the TUI.
     * @return toString() of current grid
     */
    String getGridString();
    
    /**
     * Setter for the current State of State Pattern.
     * @param currentState new State
     */
    void setCurrentState(State currentState);

    /**
     * Getter for the current State of State Pattern.
     * @return current State
     */
    State getCurrentState();
    
    /**
     * Getter for Player 1. 
     * @return player1
     */
    IPlayer getPlayer1();
    
    /**
     * Getter for Player 2.
     * @return player2
     */
    IPlayer getPlayer2();
    
    /**
     * Setter for Player 1.
     * @param name name of Player 1
     * @param character playing character of Player 1
     */
    void setPlayer1(String name, Value character);
    
    /**
     * Setter for Player 2.
     * @param name name of Player 2
     * @param character playing character of Player 2
     */
    void setPlayer2(String name, Value character);
    
    /**
     * Getter for the current Player.
     * @return current Player or null if no one is set
     */
    IPlayer getCurrentPlayer();

    /**
     * Method to initialize the current grid with Empty Cells.
     */
    void init();
    
    /**
     * Method to check if there is an empty cell in the current grid.
     * @return true if draw
     */
    boolean isDraw();
    
    /**
     * Method to check if there isn't an empty cell in the current grid.
     * @return true if empty
     */
    boolean isEmpty();
    
    /**
     * Win check of current game. Check if player1 has won, if player2 
     * has won and if game is draw.
     * @return if game is end the case, which is true as String,
     *         else String "playing"
     */
    String win();
    
    /**
     * Method to get all unset Cells and add them as String (row+""+col)
     * to a LinkedList.
     * @return List of all unset Cells as String
     */
    List<String> getUnSetCells();
    
    /**
     * Quelle: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    int randInt(int min, int max);
}

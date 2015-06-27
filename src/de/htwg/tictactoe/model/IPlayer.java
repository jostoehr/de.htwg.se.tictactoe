package de.htwg.tictactoe.model;

import de.htwg.tictactoe.util.Value;

/**
 * IPlayer is an Utitlity-Inferace.
 * @author Johannes Stöhr
 */
public interface IPlayer {
   
    /**
     * Setter for the character of a player.
     * @param character Value.CROSS or Value.NOUGHT
     */
    void setCharacter(Value character);
    
    /**
     * Getter for the character of a player.
     * @return character
     */
    Enum getCharacter();
    
    /**
     * Setter for the name of a player.
     * @param name playername
     */
    void setName(String name);
    
    /**
     * Getter for the name of a player
     * @return playername
     */
    String getName();
    
    /**
     * Setter for the Draw Count of a player
     * @param draw new count of draws
     */
    void setDrawCount(int draw);
    
    /**
     * Getter for the Draw Count of a player.
     * @return count of Draws.
     */
    int getDrawCount();
    
    /**
     * Setter for the Win Count of a player.
     * @param win new count of wins
     */
    void setWinCount(int win);
    
    /**
     * Getter for the Win Count of a player.
     * @return count of Wins.
     */
    int getWinCount();
    
        /**
     * Setter for the Win Count of a player.
     * @param win new count of wins
     */
    void setLostCount(int win);
    
    /**
     * Getter for the Win Count of a player.
     * @return count of Wins.
     */
    int getLostCount();
}

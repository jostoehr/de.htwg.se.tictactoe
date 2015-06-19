package de.htwg.tictactoe.model;

import de.htwg.tictactoe.model.impl.Player;
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
    public void setCharacter(Value character);
    
    /**
     * Getter for the character of a player.
     * @return character
     */
    public Enum getCharacter();
    
    /**
     * Setter for the name of a player.
     * @param name playername
     */
    public void setName(String name);
    
    /**
     * Getter for the name of a player
     * @return playername
     */
    public String getName();
    
    /**
     * Setter for the Draw Count of a player
     * @param draw new count of draws
     */
    public void setDrawCount(int draw);
    
    /**
     * Getter for the Draw Count of a player.
     * @return count of Draws.
     */
    public int getDrawCount();
    
    /**
     * Setter for the Win Count of a player.
     * @param win new count of wins
     */
    public void setWinCount(int win);
    
    /**
     * Getter for the Win Count of a player.
     * @return count of Wins.
     */
    public int getWinCount();
    
        /**
     * Setter for the Win Count of a player.
     * @param win new count of wins
     */
    public void setLostCount(int win);
    
    /**
     * Getter for the Win Count of a player.
     * @return count of Wins.
     */
    public int getLostCount();
}

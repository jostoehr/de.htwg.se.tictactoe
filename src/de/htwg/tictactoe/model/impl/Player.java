package de.htwg.tictactoe.model.impl;

import de.htwg.tictactoe.util.Value;
import de.htwg.tictactoe.model.IPlayer;

/**
 * Model class to save an player with name and character
 * saves the win statistics of a player
 * @author Johannes Stöhr
 */
public class Player implements IPlayer {
    private String name;
    private Value character;
    private int draw = 0;
    private int win = 0;
    private int lost = 0;
    
    public Player(String name, Value character) {
        this.name = name;
        this.character = character;
    }
    
    @Override
    public void setCharacter(Value character) {
        this.character = character;
    }
    
    @Override
    public Enum getCharacter() {
        return character;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setDrawCount(int draw) {
        this.draw = draw;
    }
    
    @Override
    public int getDrawCount() {
        return draw;
    }
    
    @Override
    public void setWinCount(int win) {
        this.win = win;
    }
    
    @Override
    public int getWinCount() {
        return win;
    }
    
    @Override
    public void setLostCount(int lost) {
        this.lost = lost;
    }
    @Override
    public int getLostCount() {
        return lost;
    }
}

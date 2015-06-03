package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
 */
public class Player {
    private String name;
    private Value character;
    private int draw = 0;
    private int win = 0;
    private int loose = 0;
    
    public Player(String name, Value character) {
        this.name = name;
        this.character = character;
    }
    
    public void setCharacter(Value character) {
        this.character = character;
    }
    
    public Enum getCharacter() {
        return character;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setDrawCount(int draw) {
        this.draw = draw;
    }
    
    public int getDrawCount() {
        return draw;
    }
    
    public void setWinCount(int win) {
        this.win = win;
    }
    
    public int getWinCount() {
        return win;
    }
    
    public void setLooseCount(int loose) {
        this.loose = loose;
    }
    public int getLooseCount() {
        return loose;
    }
}

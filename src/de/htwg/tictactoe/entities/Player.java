package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
 */
public class Player {
    private String name;
    private Enum character = Enum.EMPTY;
    private int draw = 0;
    private int win = 0;
    private int loose = 0;
    
    public Player(String name, Enum character) {
        this.name = name;
        this.character = character;
    }
    
    public void setCharacter(Enum character) {
        this.character = character;
    }
    
    public Enum getCharacter() {
        return character;
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

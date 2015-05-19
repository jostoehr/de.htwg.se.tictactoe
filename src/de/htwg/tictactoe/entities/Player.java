package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
 */
public class Player {
    private String name;
    private Enum character = Enum.EMPTY;
    
    public Player(String name, Enum character) {
        this.name = name;
        this.character = character;
    }
    
    public Enum getCharacter() {
        return character;
    }
    
    public String getName() {
        return this.name;
    }
}

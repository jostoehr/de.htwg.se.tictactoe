package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
 */

public enum Enum {
    EMPTY, CROSS, NOUGHT;
    
    /**EMPTY(' '),
    CROSS('x'), 
    NOUGHT('o');
    
    private final char character;
    Enum(char character) {
        this.character = character;
    }
    
    public char getCharacter() {
        return character;
    }
    */
                
    @Override 
    public String toString() {
        switch(this) {
            case EMPTY:
                return " ";
            case CROSS:
                return "x";
            case NOUGHT:
                return "o";
            default:
                return null;
        }
    }
}

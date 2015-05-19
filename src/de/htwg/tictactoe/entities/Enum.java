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
        String value = null;
        switch(this) {
            case EMPTY:
                value = " ";
            case CROSS:
                value = "x";
            case NOUGHT:
                value = "o";
        }
        return value;
    }
}

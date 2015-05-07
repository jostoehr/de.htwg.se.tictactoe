package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
 */

public enum Enum {
    EMPTY(0),
    CROSS(1), 
    NOUGHT(2);
    
    private final int value;
    Enum(int value) {
        this.value = value;
    }
}

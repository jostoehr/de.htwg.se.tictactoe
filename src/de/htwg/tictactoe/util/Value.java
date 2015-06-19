package de.htwg.tictactoe.util;

/**
 * Enumeration Value, which contains the possible characters.
 * @author Johannes Stöhr
 */

public enum Value {
    EMPTY, CROSS, NOUGHT;
    
    /*
    * Overrides the toString()-method
    * @return value of the enum
    */
    
                
    @Override 
    public String toString() {
        String value = null;
        switch(this) {
            case EMPTY:
                value = " ";
                break;
            case CROSS:
                value = "x";
                break;
            case NOUGHT:
                value = "o";
                break;
        }
        return value;
    }
}

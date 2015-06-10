package de.htwg.tictactoe.entities;

/**
 *
 * @author johannes
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

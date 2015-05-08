package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.entities.Enum;
import de.htwg.tictactoe.entities.Player;

/**
 *
 * @author johannes
 */
public class PlayerController {
    private Player player1;
    private Player player2;
    
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    
    public final void setPlayer1(String name, Enum character) {
        this.player1 = new Player(name, character);
    }

    public final void setPlayer2(String name, Enum character) {        
        this.player2 = new Player(name, character);
    }
}

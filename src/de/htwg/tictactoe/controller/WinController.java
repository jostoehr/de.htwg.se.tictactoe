package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.entities.Enum;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Player;
import de.htwg.util.observer.Observable;


/**
 *
 * @author johannes
 */
public class WinController extends Observable {
    
    private Grid grid;
    private Player player1;
    private Player player2;
    
    
    public WinController(Grid grid) {
        this.grid = grid;
    }
    
    public WinController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public Player win() {
        if(grid.hasWon(player1.getCharacter()))
        {
            return player1;
        }
            
        if(grid.hasWon(player2.getCharacter()))
        {
            return player2;
        }
            
        return null;
    }

}


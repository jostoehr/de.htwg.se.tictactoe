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
    
    public WinController(Grid grid, Player player1, Player player2) {
        this.grid = grid;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public String win() {
        String win = null;
        if(grid.hasWon(player1.getCharacter()))
            win =  player1.getName();
        else if(grid.hasWon(player2.getCharacter()))
            win =  player2.getName();
        else if(grid.isDraw()) {
            win = "draw";
        } else {
            return "playing";
        }
        return win;
    }
    
    /*
    public void winCheck(int row, int column) {
        try {
            grid.getCell(row, column);
        } catch {
            
        } finally {
            
        }
    }
     * neue Gewinnüberprüfungsidee:
     * letzten gesetzten Wert übergeben und von 
     * diesem aus in alle Richtungen überprüfen
     */
}


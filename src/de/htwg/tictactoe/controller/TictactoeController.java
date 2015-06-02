package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.controller.impl.StateCrossWon;
import de.htwg.tictactoe.controller.impl.StateDraw;
import de.htwg.tictactoe.controller.impl.StateNoughtPlaying;
import de.htwg.tictactoe.controller.impl.StateNoughtWon;
import de.htwg.tictactoe.entities.Cell;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Enum;
import static de.htwg.tictactoe.entities.Grid.COLS;
import static de.htwg.tictactoe.entities.Grid.ROWS;
import de.htwg.tictactoe.entities.Player;
import de.htwg.util.observer.Observable;

/**
 *
 * @author siegfried
 */
public class TictactoeController extends Observable {

    private String statusMessage = "Welcome to HTWG TicTacToe!";
    private final Grid grid;
    private IGameState currentState;
    private Player player1;
    private Player player2;

    public TictactoeController(Grid grid) {
        this.grid = grid;
    }
    
    public void setValue(int row, int column) {
        Cell cell = grid.getCell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(Enum.CROSS);
            if (currentState instanceof StateNoughtPlaying) {
                cell.setValue(Enum.NOUGHT);
            }
            setStatusMessage("The cell " + cell.mkString() + " was successfully set\n");
        } else {
            setStatusMessage("The cell " + cell.mkString() + " is already set\n");
        }
        getCurrentState().change();
        if(this.currentState instanceof StateNoughtWon
                || this.currentState instanceof StateCrossWon
                || this.currentState instanceof StateDraw) {
            updateStatistic();
        }
        notifyObservers();
    }

    public String getStatus() {
        return statusMessage;
    }

    private void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getGridString() {
        return grid.toString();
    }

    public void setCurrentState(IGameState currentState) {
        this.currentState = currentState;
    }

    public IGameState getCurrentState() {
        return currentState;
    }

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

    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                /* clear the cell content */
                grid.getCell(row, col).clear();
            }
        }
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isUnSet()) {
                    /* an empty content found, not a draw */
                    return false;
                }
            }
        }
        /* no empty cell, draw! */
        return true;
    }
    
    public boolean isEmpty() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isSet()) {
                    /* an empty content found, not a draw */
                    return false;
                }
            }
        }
        /* no empty cell, draw! */
        return true;
    }

    public boolean hasWon(Enum p) {
        for (int i=0; i<3; i++) {
            if (grid.getCell(i,0).getValue() == p && equal(i,0, i,1, i,2)) {
                return true;
            }
            if (grid.getCell(0,i).getValue() == p && equal(0,i, 1,i, 2,i)) {
                return true;
            }
        }
        if (grid.getCell(0,0).getValue() == p && equal(0,0, 1,1, 2,2)) {
            return true;
        }
        if (grid.getCell(0,2).getValue() == p && equal(0,2, 1,1, 2,0)) {
            return true;
        }
        return false;
    }

    public boolean equal(int r0, int c0, int r1, int c1, int r2, int c2) {
        return(grid.getCell(r0, c0).getValue() == grid.getCell(r1, c1).getValue() &&
                grid.getCell(r1, c1).getValue() == grid.getCell(r2, c2).getValue());
    }

    public String win() {
        String win = null;
        if (hasWon(player1.getCharacter())) {
            win = player1.getName();
        } else if (hasWon(player2.getCharacter())) {
            win = player2.getName();
        } else if (isDraw()) {
            win = "draw";
        } else {
            return "playing";
        }
        return win;
    }
    
    private void updateStatistic() {
        if(win().equals(player1.getName())) {
            player1.setWinCount(player1.getWinCount() +1);
            player2.setLooseCount(player2.getLooseCount() + 1);
        } else if(win().equals(player2.getName())) {
            player2.setWinCount(player2.getWinCount() +1);
            player1.setLooseCount(player1.getLooseCount() +1);
        } else {
            player1.setDrawCount(player1.getDrawCount() + 1);
            player2.setDrawCount(player2.getDrawCount() + 1);
        }
    }    
}

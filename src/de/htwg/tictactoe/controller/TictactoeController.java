package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.controller.impl.State;
import de.htwg.tictactoe.entities.Cell;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Value;
import static de.htwg.tictactoe.entities.Grid.COLS;
import static de.htwg.tictactoe.entities.Grid.ROWS;
import de.htwg.tictactoe.entities.Player;
import de.htwg.util.observer.Observable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author siegfried
 */
public class TictactoeController extends Observable {

    private String statusMessage = "Welcome to HTWG TicTacToe!";
    private final Grid grid;
    private State currentState;
    private Player player1;
    private Player player2;

    public TictactoeController(Grid grid) {
        this.grid = grid;
        setPlayer1("default", Value.CROSS);
        setPlayer2("default", Value.NOUGHT);
    }
    
    public void setValue(int row, int column) {
        Cell cell = grid.getCell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(Value.CROSS);
            if (currentState == State.StateNoughtPlaying) {
                cell.setValue(Value.NOUGHT);
            }
            setStatusMessage("The cell " + cell.toString() + " was successfully set\n");
            change();
        } else {
            setStatusMessage("The cell " + cell.toString() + " is already set\n");
        }
        if(this.currentState == State.StateNoughtWon
                || this.currentState == State.StateCrossWon
                || this.currentState == State.StateDraw) {
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

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public final void setPlayer1(String name, Value character) {
        this.player1 = new Player(name, character);
    }

    public final void setPlayer2(String name, Value character) {
        this.player2 = new Player(name, character);
    }
    
    public Player getCurrentPlayer() {
        if(getCurrentState() == State.StateCrossPlaying) {
            if(getPlayer1().getCharacter().equals(Value.CROSS)) {
                return getPlayer1();
            } else {
                return getPlayer2();
            }
        } else if(getCurrentState() == State.StateNoughtPlaying) {
            if(getPlayer2().getCharacter().equals(Value.NOUGHT)) {
                return getPlayer2();
            } else {
                return getPlayer1();
            }
        } else {
            return null;
        }
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

    /**
     * Win Ceck with hasWon and equal idea from:
     * Quelle: http://wiki.byte-welt.net/wiki/Entwurfsmuster_%28Design_Patterns%29#Beispiel_von_TicTacToe 
     */
    public boolean hasWon(Enum p) {
        for (int i=0; i<ROWS; i++) {
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
    
    public void change() {
        if(win().equals("playing")) {
            if(getCurrentState() == State.StateCrossPlaying) {
                setCurrentState(State.StateNoughtPlaying);
            } else {
                setCurrentState(State.StateCrossPlaying);
            }
        } else {
            if(win().equals(getPlayer1().getName())) {
                setCurrentState(State.StateCrossWon);
            }
            if(win().equals(getPlayer2().getName())) {
                setCurrentState(State.StateNoughtWon);
            }
            if(win().equals("draw")) {
                setCurrentState(State.StateDraw);
            }
        }
    }
    
    public List<String> getUnSetCells() {
        List<String> l = new LinkedList<>();
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isUnSet()) {
                    String cell = row+""+col;
                    l.add(cell);
                }
            }
        }
        return l;
    }
    
    /**
     * Quelle: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

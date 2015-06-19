package de.htwg.tictactoe.controller.impl;

import de.htwg.tictactoe.controller.IMasterController;
import de.htwg.tictactoe.model.ICell;
import de.htwg.tictactoe.model.IGrid;
import de.htwg.tictactoe.model.IPlayer;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.util.Value;
import static de.htwg.tictactoe.model.impl.Grid.COLS;
import static de.htwg.tictactoe.model.impl.Grid.ROWS;
import de.htwg.tictactoe.model.impl.Player;
import de.htwg.util.observer.Observable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Master Controller class to controlles the game with methods 
 * between Models and Views.
 * @author Siegfried Kienzle
 */
public class MasterController extends Observable implements IMasterController {

    private String statusMessage = "Welcome to HTWG TicTacToe!";
    private final IGrid grid;
    private State currentState;
    private IPlayer player1;
    private IPlayer player2;

    public MasterController(IGrid grid) {
        this.grid = grid;
        setPlayer1("default1", Value.CROSS);
        setPlayer2("default2", Value.NOUGHT);
    }
    
    @Override
    public void setValue(int row, int column) {
        ICell cell = grid.getCell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(Value.CROSS);
            if (currentState == State.STATENOUGHTPLAYING) {
                cell.setValue(Value.NOUGHT);
            }
            setStatusMessage("The cell " + cell.toString() + " was successfully set\n");
            change();
        } else {
            setStatusMessage("The cell " + cell.toString() + " is already set\n");
        }
        if(this.currentState == State.STATENOUGHTWON
                || this.currentState == State.STATECROSSWON
                || this.currentState == State.STATEDRAW) {
            updateStatistic();
        }
        notifyObservers();
    }

    @Override
    public String getStatus() {
        return statusMessage;
    }

    private void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String getGridString() {
        return grid.toString();
    }

    @Override
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public IPlayer getPlayer1() {
        return player1;
    }

    @Override
    public IPlayer getPlayer2() {
        return player2;
    }

    @Override
    public final void setPlayer1(String name, Value character) {
        this.player1 = new Player(name, character);
    }

    @Override
    public final void setPlayer2(String name, Value character) {
        this.player2 = new Player(name, character);
    }
    
    @Override
    public IPlayer getCurrentPlayer() {
        if(getCurrentState() == State.STATECROSSPLAYING) {
            if(getPlayer1().getCharacter().equals(Value.CROSS)) {
                return getPlayer1();
            } else {
                return getPlayer2();
            }
        } else if(getCurrentState() == State.STATENOUGHTPLAYING) {
            if(getPlayer2().getCharacter().equals(Value.NOUGHT)) {
                return getPlayer2();
            } else {
                return getPlayer1();
            }
        } else {
            return null;
        }
    }

    @Override
    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                /* clear the cell content */
                grid.getCell(row, col).clear();
            }
        }
    }

    @Override
    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isUnSet()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean isEmpty() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isSet()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check win situation of an character.
     * Quelle: http://wiki.byte-welt.net/wiki/Entwurfsmuster_%28Design_Patterns%29#Beispiel_von_TicTacToe
     * @param p character, which has to be checked
     * @return 
     */
    private boolean hasWon(Enum p) {
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
        return grid.getCell(0,2).getValue() == p && equal(0,2, 1,1, 2,0);
    }

    /**
     * Helping function for hasWon() Method.
     * Checks 3 cells of matching in character.
     * Quelle: http://wiki.byte-welt.net/wiki/Entwurfsmuster_%28Design_Patterns%29#Beispiel_von_TicTacToe
     * @param r0 row of first cell
     * @param c0 column of first cell
     * @param r1 row of secend cell
     * @param c1 column of second cell
     * @param r2 row of third cell   
     * @param c2 column of third cell   
     * @return true if these 3 cells match in character
     */
    private boolean equal(int r0, int c0, int r1, int c1, int r2, int c2) {
        return(grid.getCell(r0, c0).getValue() == grid.getCell(r1, c1).getValue() &&
                grid.getCell(r1, c1).getValue() == grid.getCell(r2, c2).getValue());
    }

    @Override
    public String win() {
        String win;
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
    
    /**
     * Method to update the win statistics of player1 and player2.
     */
    private void updateStatistic() {
        if(win().equals(player1.getName())) {
            player1.setWinCount(player1.getWinCount() +1);
            player2.setLostCount(player2.getLostCount() + 1);
        } else if(win().equals(player2.getName())) {
            player2.setWinCount(player2.getWinCount() +1);
            player1.setLostCount(player1.getLostCount() +1);
        } else {
            player1.setDrawCount(player1.getDrawCount() + 1);
            player2.setDrawCount(player2.getDrawCount() + 1);
        }
    }
    
    /**
     * Change Method of State Pattern to get the current state and update it.
     */
    private void change() {
        if(win().equals("playing")) {
            if(getCurrentState() == State.STATECROSSPLAYING) {
                setCurrentState(State.STATENOUGHTPLAYING);
            } else {
                setCurrentState(State.STATECROSSPLAYING);
            }
        } else {
            if(win().equals(getPlayer1().getName())) {
                setCurrentState(State.STATECROSSWON);
            }
            if(win().equals(getPlayer2().getName())) {
                setCurrentState(State.STATENOUGHTWON);
            }
            if(win().equals("draw")) {
                setCurrentState(State.STATEDRAW);
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
    
    @Override
    public int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum; 
        randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

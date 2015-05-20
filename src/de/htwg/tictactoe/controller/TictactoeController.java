package de.htwg.tictactoe.controller;
import de.htwg.tictactoe.controller.impl.StateCrossPlaying;
import de.htwg.tictactoe.controller.impl.StateCrossWon;
import de.htwg.tictactoe.controller.impl.StateDraw;
import de.htwg.tictactoe.controller.impl.StateNoughtPlaying;
import de.htwg.tictactoe.controller.impl.StateNoughtWon;
import de.htwg.tictactoe.entities.Cell;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Enum;
import de.htwg.util.observer.Observable;

/**
 *
 * @author siegfried
 */
public class TictactoeController extends Observable {
    
    private String statusMessage = "Welcome to HTWG TicTacToe!";
    private final Grid grid;
    private IGameState currentState;
    
    public TictactoeController(Grid grid) {
        this.grid = grid;
        grid.init();
    }
    
    public void setValue(int row, int column) {
	Cell cell = grid.getCell(row, column);
	if (cell.isUnSet()) { 
            if(currentState instanceof StateCrossPlaying) {
                System.out.println("cross");
                cell.setValue(Enum.CROSS);
            }
            if(currentState instanceof StateNoughtPlaying)
                cell.setValue(Enum.NOUGHT);
            setStatusMessage("The cell " + cell.mkString() + " was successfully set\n");
	} else {
            setStatusMessage("The cell " + cell.mkString() + " is already set\n");
        }
        getCurrentState().change();
	notifyObservers();
    }
    
    
    public void setValue(int row, int column, Enum value) {
	Cell cell = grid.getCell(row, column);
	if (cell.isUnSet()) { 
                cell.setValue(value);
                setStatusMessage("The cell " + cell.mkString() + " was successfully set\n");
	} else {
            setStatusMessage("The cell " + cell.mkString() + " is already set\n");
        }
        getCurrentState().change();
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
    
    public void endGame() {
        if(this.currentState instanceof StateNoughtWon ||
                this.currentState instanceof StateCrossWon ||
                this.currentState instanceof StateDraw) {
            
        }
    }
}

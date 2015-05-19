package de.htwg.tictactoe.controller;
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
    private Grid grid;
    
    public TictactoeController(Grid grid) {
        this.grid = grid;
        grid.init();
    }
    
    public void setValue(int row, int column, Enum value) {
	Cell cell = grid.getCell(row, column);
	if (cell.isUnSet()) {
		cell.setValue(value);
		setStatusMessage("The cell " + cell.mkString() + " was successfully set\n");
	} else {
            setStatusMessage("The cell " + cell.mkString() + " is already set\n");
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
}

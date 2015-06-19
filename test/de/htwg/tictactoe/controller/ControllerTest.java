package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.model.impl.Cell;
import de.htwg.tictactoe.model.impl.Grid;
import de.htwg.tictactoe.util.Value;
import static de.htwg.tictactoe.model.impl.Grid.COLS;
import static de.htwg.tictactoe.model.impl.Grid.ROWS;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author siegfried
 */
public class ControllerTest {
    private Grid grid;
    private MasterController controller;
    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        controller = new MasterController(grid);
        controller.setPlayer1("Hans", Value.CROSS);
        controller.setPlayer2("Karle", Value.NOUGHT);
        controller.init();
        controller.setCurrentState(State.STATECROSSPLAYING);
    }
    
    @Test
    public void testGetValue(){
        controller.setValue(0, 0);
        controller.setValue(0, 0);
        String string = "The cell (0,0) = x is already set\n";
        assertEquals(string ,controller.getStatus());
        controller.setValue(1, 1);
        controller.setCurrentState(State.STATENOUGHTWON);
        
    }
    
    @Test
    public void testGetStatus(){
        assertEquals("Welcome to HTWG TicTacToe!", controller.getStatus());
    }
    
    @Test
    public void testGetGridString(){
        assertEquals(grid.toString(), controller.getGridString());
    }
    
    @Test
    public void testIsDraw(){
        controller.init();
        
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                controller.setValue(i, j);
            }
        }
        assertTrue(controller.isDraw());
        assertFalse(controller.isEmpty());
        controller.init();
        assertTrue(controller.isEmpty());
        assertFalse(controller.isDraw());
        
        
    }
    
    @Test
    public void testGetCurrentPlayer(){
        assertEquals(controller.getCurrentState(), State.STATECROSSPLAYING);
        assertEquals(controller.getPlayer1(), controller.getCurrentPlayer());
        controller.getCurrentPlayer().setCharacter(Value.NOUGHT);
        assertEquals(controller.getPlayer2(), controller.getCurrentPlayer());
        controller.setCurrentState(State.STATENOUGHTPLAYING);
        assertEquals(controller.getPlayer2(), controller.getCurrentPlayer());
        
        controller.setCurrentState(State.STATENOUGHTPLAYING);
        controller.getCurrentPlayer().setCharacter(Value.CROSS);
        assertEquals(controller.getPlayer1(), controller.getCurrentPlayer());
        
        
        controller.setCurrentState(State.STATEDRAW);
        assertEquals(null, controller.getCurrentPlayer());
        
    }
    
    @Test
    public void testRandInt(){
        assertEquals(MasterController.randInt(2,2), MasterController.randInt(2,2));
    }
    @Test
    public void testGetUnSetCells(){
        List<String> l = new LinkedList<>();
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (grid.getCell(row, col).isUnSet()) {
                    String cell = row+""+col;
                    l.add(cell);
                }
            }
        }
        assertEquals(l, controller.getUnSetCells());
    }
    
    @Test
    public void testHasWon(){
        controller.setValue(0, 0);
        controller.setValue(0, 1);
        controller.setValue(1, 1);
        controller.setValue(0, 2);
        controller.setValue(2, 2);
        assertEquals(true, controller.hasWon(Value.CROSS));
        assertEquals("Hans", controller.win());
        controller.setValue(0, 0);
        controller.setValue(0, 1);
        controller.setValue(1, 0);
        controller.setValue(1, 1);
        controller.setValue(2, 0);
        assertEquals(true, controller.hasWon(Value.CROSS));
        
        
    }
    
    @Test
    public void testWin(){
        controller.setValue(0, 1);
        controller.setValue(0, 0);
        controller.setValue(1, 1);
        controller.setValue(2, 0);
        controller.setValue(1, 0);
        assertEquals("playing", controller.win());
        controller.setPlayer1("Hansi", Value.NOUGHT);
        controller.setPlayer2("Peterle", Value.CROSS);
        controller.setValue(0, 1);
        controller.setValue(0, 0);
        controller.setValue(0, 2);
        controller.setValue(1, 1);
        controller.setValue(1, 2);
        controller.setValue(2, 2);
        assertEquals("Peterle", controller.win());
        
        
        
        
        /*
        *
        *        drawTest
        */
        /*controller.init();
        controller.setValue(0, 0);
        controller.setValue(0, 1);
        controller.setValue(0, 2);
        controller.setValue(1, 0);
        controller.setValue(1, 2);
        controller.setValue(1, 1);
        controller.setValue(2, 0);
        controller.setValue(2, 2);
        controller.setValue(2, 1);
        controller.setValue(1,1);
        assertEquals("draw", controller.win());*/

        
        
    }
    
}

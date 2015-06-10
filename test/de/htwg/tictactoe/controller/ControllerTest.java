/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.controller.impl.State;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Value;
import static de.htwg.tictactoe.entities.Grid.COLS;
import static de.htwg.tictactoe.entities.Grid.ROWS;
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
    private TictactoeController controller;
    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        controller = new TictactoeController(grid);
        controller.setPlayer1("Hans", Value.CROSS);
        controller.setPlayer2("Karle", Value.NOUGHT);
        controller.init();
        controller.setCurrentState(State.STATECROSSPLAYING);
    }
    
    @Test
    public void testGetValue(){
        controller.setValue(0, 0);
        
      /*  assertEquals(0, grid.getCell(0, 0).getRow());
        assertEquals(0, grid.getCell(0, 0).getColumn());
        assertEquals(Enum.CROSS, grid.getCell(0,0).getValue());
        assertEquals("The cell (0,0) = x was successfully set", controller.getStatus());
        Enum value2 = Enum.EMPTY;
        controller.setValue(0, 1,value2 );
        assertEquals(0, grid.getCell(0, 1).getRow());
        assertEquals(1, grid.getCell(0, 1).getColumn());
        assertEquals(value2, grid.getCell(0,1).getValue());
        assertEquals("The cell (0,1) = x was successfully set", controller.getStatus());*/
        
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
        
    }
    
    @Test
    public void testRandInt(){
        assertEquals(TictactoeController.randInt(2,2), TictactoeController.randInt(2,2));
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
        //controller.setValue(0, 0);
        //controller.setValue(1, 1);
        //controller.setValue(2, 2);
        //grid.setCell(0, 0, Value.NOUGHT);
        //assertEquals(true ,controller.hasWon(Value.CROSS));
    }
    
    @Test
    public void testWin(){
        //controller.getPlayer2().setWinCount(COLS);
    }
    
}

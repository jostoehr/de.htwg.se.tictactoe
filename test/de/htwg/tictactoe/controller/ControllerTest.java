/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.entities.Cell;
import de.htwg.tictactoe.entities.Grid;
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
    }
    
    @Test
    public void testGetValue(){
        char zeichen = 120;
        controller.setValue(0, 0, zeichen);
        assertEquals(0, grid.getCell(0, 0).getRow());
        assertEquals(0, grid.getCell(0, 0).getColumn());
        //assertEquals(zeichen, grid.getCell(0,0).getValue());
        //assertEquals("The cell (0,0) = x was successfully set", controller.getStatus());
        
    }
    
}

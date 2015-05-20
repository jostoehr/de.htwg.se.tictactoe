/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.entities;

import static de.htwg.tictactoe.entities.Grid.COLS;
import static de.htwg.tictactoe.entities.Grid.ROWS;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siegfried
 */
public class GridTest {
    private Grid grid; 
    private Grid grid2;
    private Cell cell[][];
    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        grid2 = new Grid();
        
    }

    @Test
    public void testGetValue(){
        grid.setCell(0, 0, Enum.NOUGHT);
        grid2.setCell(1, 1, Enum.EMPTY);
        assertEquals(grid.getCell(0, 0), grid.getCell(0, 0));
        
    }
    @Test
    public void testIsDraw(){
        assertTrue(grid.isDraw());
       // assertFalse(grid2.isDraw());
    }
    
}

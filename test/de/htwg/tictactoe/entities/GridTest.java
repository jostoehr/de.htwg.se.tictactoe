/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.entities;

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
    private Cell cell[][];
    @Before
    public void setUp() throws Exception {
        grid = new Grid();
    }

    @Test
    public void testGetValue(){
    }
    
    @Test
    public void testGetSetCell(){
        grid.setCell(0, 0, Enum.CROSS);
    }
}

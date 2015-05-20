package de.htwg.tictactoe.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class CellTest {
    Cell cell;
    @Before
    public void setUp() throws Exception {
        cell = new Cell(3,3);
        cell.setValue(Enum.CROSS);
    }

    @Test
    public void testGetValue(){
        assertEquals(Enum.CROSS, cell.getValue());
    }
    
    @Test
    public void testIsSetOrUnSet() {
    }
    @Test
    public void testClear(){
       cell.clear();
       assertEquals(Enum.EMPTY, cell.getValue());
    }
    @Test
    public void testGetRow(){
        assertEquals(3, cell.getRow());
    }
    @Test
    public void testGetColumn(){
        assertEquals(3, cell.getColumn());
    }
    

}

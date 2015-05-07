package de.htwg.tictactoe.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class CellTest {
    Cell cell;
    @Before
    public void setUp() throws Exception {
        cell = new Cell(3,3);
    }

    @Test
    public void testGetValue(){
        cell.Enum('o');
        assertEquals('o', cell.getValue());
        cell.Enum('x');
        assertEquals('x', cell.getValue());
    }
    
    @Test
	public void testIsSetOrUnSet() {
	/*	assertFalse(cell.isSet());
		assertTrue(cell.isUnSet());
		cell.setValue('x');
		assertTrue(cell.isSet());
		assertFalse(cell.isUnSet());
		cell.setValue('o');
		assertFalse(cell.isSet());
		assertTrue(cell.isUnSet());*/
	}
    @Test
    public void testClear(){
       cell.clear();
       assertEquals(' ', cell.getValue());
    }

}

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
        /*Enum value = Enum.NOUGHT;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
        value = Enum.CROSS;
        cell.setValue(value);
        assertEquals(value, cell.getValue());*/
    }
    
    @Test
    public void testIsSetOrUnSet() {
       /* assertFalse(cell.isSet());
        assertTrue(cell.isUnSet());
	cell.setValue(Enum.CROSS);
	assertTrue(cell.isSet());
	assertFalse(cell.isUnSet());
	cell.setValue(Enum.NOUGHT);
	assertFalse(cell.isSet());
	assertTrue(cell.isUnSet());*/
    }
    @Test
    public void testClear(){
       cell.clear();
       assertEquals(Enum.EMPTY, cell.getValue());
    }

}

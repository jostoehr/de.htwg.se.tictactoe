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
        cell.setValue('o');
        assertEquals('o', cell.getValue());
        cell.setValue('x');
        assertEquals('x', cell.getValue());
    }


}

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
public class EnumTest {
    private Enum cross;
    private Enum nought;
    private Enum empty;
    @Before
    public void setUp() {
        cross = Value.CROSS;
        nought = Value.NOUGHT;
        empty = Value.EMPTY;
    }
    @Test
    public void TestToString(){
        assertEquals("x", cross.toString());
        assertEquals("o", nought.toString());
        assertEquals(" ", empty.toString());
        assertEquals(Value.EMPTY, Value.valueOf("EMPTY"));
        assertEquals(Value.CROSS, Value.valueOf("CROSS"));
        assertEquals(Value.NOUGHT, Value.valueOf("NOUGHT"));
    }
}

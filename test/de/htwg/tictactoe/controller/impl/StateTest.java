/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.controller.impl;

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
public class StateTest {
    private Enum STATECROSSPLAYING;
    private Enum STATENOUGHTPLAYING;
    private Enum STATECROSSWON;
    private Enum STATENOUGHTWON;
    private Enum STATEDRAW;
    @Before
    public void setUp() throws Exception {
        STATECROSSPLAYING = State.STATECROSSPLAYING;
        STATENOUGHTPLAYING = State.STATENOUGHTPLAYING;
        STATECROSSWON = State.STATECROSSWON;
        STATENOUGHTWON = State.STATENOUGHTWON;
        STATEDRAW = State.STATEDRAW;
    }
    @Test
    public void TestValueOf(){
        assertEquals(State.STATECROSSPLAYING, State.valueOf("STATECROSSPLAYING"));
        assertEquals(State.STATENOUGHTPLAYING, State.valueOf("STATENOUGHTPLAYING"));
        assertEquals(State.STATECROSSWON, State.valueOf("STATECROSSWON"));
        assertEquals(State.STATENOUGHTWON, State.valueOf("STATENOUGHTWON"));
        assertEquals(State.STATEDRAW, State.valueOf("STATEDRAW"));
    }
    
    
}

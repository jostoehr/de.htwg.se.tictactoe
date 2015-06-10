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
    private Enum StateCrossPlaying;
    private Enum StateNoughtPlaying;
    private Enum StateCrossWon;
    private Enum StateNoughtWon;
    private Enum StateDraw;
    @Before
    public void setUp() throws Exception {
        StateCrossPlaying = State.STATECROSSPLAYING;
        StateNoughtPlaying = State.STATENOUGHTPLAYING;
        StateCrossWon = State.STATECROSSWON;
        StateNoughtWon = State.STATENOUGHTWON;
        StateDraw = State.STATEDRAW;
    }
    @Test
    public void TestValueOf(){
        assertEquals(State.STATECROSSPLAYING, State.valueOf("StateCrossPlaying"));
        assertEquals(State.STATENOUGHTPLAYING, State.valueOf("StateNoughtPlaying"));
        assertEquals(State.STATECROSSWON, State.valueOf("StateCrossWon"));
        assertEquals(State.STATENOUGHTWON, State.valueOf("StateNoughtWon"));
        assertEquals(State.STATEDRAW, State.valueOf("StateDraw"));
    }
    
    
}

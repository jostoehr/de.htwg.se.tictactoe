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
        StateCrossPlaying = State.StateCrossPlaying;
        StateNoughtPlaying = State.StateNoughtPlaying;
        StateCrossWon = State.StateCrossWon;
        StateNoughtWon = State.StateNoughtWon;
        StateDraw = State.StateDraw;
    }
    @Test
    public void TestValueOf(){
        assertEquals(State.StateCrossPlaying, State.valueOf("StateCrossPlaying"));
        assertEquals(State.StateNoughtPlaying, State.valueOf("StateNoughtPlaying"));
        assertEquals(State.StateCrossWon, State.valueOf("StateCrossWon"));
        assertEquals(State.StateNoughtWon, State.valueOf("StateNoughtWon"));
        assertEquals(State.StateDraw, State.valueOf("StateDraw"));
        //assertEquals(State.valueOf("StateCrossPlaying"));
    }
    
    
}

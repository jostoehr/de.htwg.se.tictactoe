/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.model.impl.Grid;
import static de.htwg.tictactoe.model.impl.Grid.COLS;
import static de.htwg.tictactoe.model.impl.Grid.ROWS;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.util.Value;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author johannes
 */
public class Test {
    private Grid grid;
    private MasterController controller;
    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        controller = new MasterController();
        controller.setPlayer1("Depp", Value.CROSS);
        controller.setPlayer2("Karle", Value.NOUGHT);
        controller.init();
        controller.setCurrentState(State.STATECROSSPLAYING);
    }
    
    @org.junit.Test
    public void testWin(){
        controller.init();
        controller.setCurrentState(State.STATECROSSPLAYING);
        assertTrue(controller.isEmpty());
        assertEquals("playing", controller.win());
        controller.setValue(0, 1);
        controller.setValue(0, 0);
        controller.setValue(1, 0);
        controller.setValue(0, 2);
        controller.setValue(1, 2);
        controller.setValue(1, 1);
        controller.setValue(2, 0);
        controller.setValue(2, 1);
        controller.setValue(2, 2);
        System.out.println(controller.win());
        assertEquals("draw", controller.win());
    }
    
}

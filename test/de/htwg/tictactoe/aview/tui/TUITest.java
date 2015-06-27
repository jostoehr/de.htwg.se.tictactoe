/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.aview.tui;

import de.htwg.tictactoe.controller.IMasterController;
import de.htwg.tictactoe.controller.impl.MasterController;
import java.util.logging.Logger;
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
public class TUITest {
    
    private TUI tui;
    private MasterController controller;
    
    
    static Logger logger = Logger.getLogger("TUITest.class");
    private Object PropertyConfigurator;
    
    @Before
    public void setUp() throws Exception {
        controller = new MasterController();
        controller.init();
        tui = new TUI(controller);
    }
    
    @Test
    public void modeChange() {
        tui.processInputLine("h");
        System.out.println("hallo");
        
    }
    
    
}

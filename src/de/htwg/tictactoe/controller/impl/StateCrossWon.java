package de.htwg.tictactoe.controller.impl;

import de.htwg.tictactoe.controller.IGameState;
import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.WinController;

/**
 *
 * @author johannes
 */
public class StateCrossWon implements IGameState {
    
    private TictactoeController controller;
    private PlayerController pcontroller;
    private WinController wcontroller;
    
    public StateCrossWon(final TictactoeController controller,
            final PlayerController pcontroller, final WinController wcontroller) {
        this.controller = controller;
        this.pcontroller = pcontroller;
        this.wcontroller = wcontroller;
    }
    
    @Override
    public void change() {
        
    }
}

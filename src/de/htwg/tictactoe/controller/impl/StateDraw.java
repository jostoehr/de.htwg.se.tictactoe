package de.htwg.tictactoe.controller.impl;

import de.htwg.tictactoe.controller.IGameState;
import de.htwg.tictactoe.controller.TictactoeController;

/**
 *
 * @author Johannes Stöhr
 */
public class StateDraw implements IGameState {

    private TictactoeController controller;
    
    public StateDraw(final TictactoeController controller) {
        this.controller = controller;
    }
    
    @Override
    public void change() {
        
    }    
}

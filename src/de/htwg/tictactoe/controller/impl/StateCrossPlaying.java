package de.htwg.tictactoe.controller.impl;

import de.htwg.tictactoe.controller.IGameState;
import de.htwg.tictactoe.controller.TictactoeController;

/**
 *
 * @author Johannes Stöhr
 */
public class StateCrossPlaying implements IGameState {

    private TictactoeController controller;
    
    public StateCrossPlaying(final TictactoeController controller){
        this.controller = controller;
    }
    
    @Override
    public void change() {
        if(controller.win().equals("playing"))
            controller.setCurrentState(new StateNoughtPlaying(controller));
        else {
            if(controller.win().equals(controller.getPlayer1().getName())) {
                controller.setCurrentState(new StateCrossWon(controller));
                controller.getCurrentState().change();
            }
            if(controller.win().equals(controller.getPlayer2().getName())) {
                controller.setCurrentState(new StateNoughtWon(controller));
                controller.getCurrentState().change();
            }
            if(controller.win().equals("draw")) {
                controller.setCurrentState(new StateDraw(controller));
                controller.getCurrentState().change();
            }
        }
    }      
}

package de.htwg.tictactoe.controller.impl;

import de.htwg.tictactoe.controller.IGameState;
import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.WinController;

/**
 *
 * @author johannes
 */
public class StateCrossPlaying implements IGameState {

    private TictactoeController controller;
    private PlayerController pcontroller;
    private WinController wcontroller;
    
    public StateCrossPlaying(final TictactoeController controller,
            final PlayerController pcontroller, final WinController wcontroller) {
        this.controller = controller;
        this.pcontroller = pcontroller;
        this.wcontroller = wcontroller;
    }
    
    @Override
    public void change() {
        if(wcontroller.win().equals("playing"))
            controller.setCurrentState(new StateNoughtPlaying(controller,
                pcontroller, wcontroller));
        else {
            if(wcontroller.win().equals(pcontroller.getPlayer1().getName())) {
                controller.setCurrentState(new StateCrossWon(controller,
                pcontroller, wcontroller));
                controller.getCurrentState().change();
            }
            if(wcontroller.win().equals(pcontroller.getPlayer2().getName())) {
                controller.setCurrentState(new StateNoughtWon(controller,
                pcontroller, wcontroller));
                controller.getCurrentState().change();
            }
            if(wcontroller.win().equals("draw")) {
                controller.setCurrentState(new StateDraw(controller,
                pcontroller, wcontroller));
                controller.getCurrentState().change();
            }
        }
    }      
}

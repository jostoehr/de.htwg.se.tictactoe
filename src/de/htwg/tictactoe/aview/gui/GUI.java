package de.htwg.tictactoe.aview.gui;

import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.util.observer.IObserver;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author siegfried
 */
public class GUI implements IObserver {
    
    /* Controller Instance Variable */
    private MasterController master;
    
    public GUI(MasterController master) {
        master.addObserver(this);
        this.master = master;
        Application.launch(ModePlayer.class);
        //Application.launch(GameField.class);
    }

    @Override
    public void update() {
        
    }
}

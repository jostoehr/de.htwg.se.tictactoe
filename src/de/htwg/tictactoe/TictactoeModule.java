/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe;

import com.google.inject.AbstractModule;
import de.htwg.tictactoe.controller.IMasterController;

/**
 *
 * @author siegfried
 */
public class TictactoeModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(IMasterController.class).to(de.htwg.tictactoe.controller.impl.MasterController.class);
    }
    
}

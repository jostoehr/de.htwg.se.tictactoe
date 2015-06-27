package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.util.observer.IObserver;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Johannes Stöhr
 */
public class GUISwing extends JFrame implements IObserver {
    
    private MasterController master;
    
    @Inject
    public GUISwing(MasterController master) {
        this.master = master;

        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setJMenuBar(new GameMenuBar());
        pack();
        setSize(700, 700);
        setResizable(false);
        setVisible(true);
        
        Toolkit tk = getToolkit();
        
        setLocation((tk.getScreenSize().width-this.getWidth())/2,
                    (tk.getScreenSize().height-this.getHeight())/2);
        
        //new ModePlayerSwing(this.master);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

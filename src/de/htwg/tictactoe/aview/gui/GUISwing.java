package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.util.observer.IObserver;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
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
    private int WIDTH = 700;
    private int HEIGHT = 700;
    
    @Inject
    public GUISwing(MasterController master) {
        this.master = master;

        setTitle("TicTacToe");
        URL iconURL = getClass().getResource("icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setJMenuBar(new GameMenuBar());
        pack();
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        
        Toolkit tk = getToolkit();
        
        setLocation((tk.getScreenSize().width-this.getWidth())/2,
                    (tk.getScreenSize().height-this.getHeight())/2);
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

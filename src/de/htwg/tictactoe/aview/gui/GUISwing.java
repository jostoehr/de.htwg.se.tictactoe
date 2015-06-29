package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.util.observer.IObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Johannes Stöhr
 */
public class GUISwing extends JFrame implements IObserver, ActionListener {
    
    private final MasterController master;
    
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem modePlayer;
    private JMenuItem change;
    private JMenuItem close;
    
    private JButton buttons[];
    
    ImageIcon X,O;
    
    @Inject
    public GUISwing(MasterController master) {
        this.master = master;

        setTitle("TicTacToe");
        URL iconURL = getClass().getResource("icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setMenu();
        
        X = new ImageIcon(this.getClass().getResource("cross2.png"));
        O = new ImageIcon(this.getClass().getResource("nought.png"));
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(3,3));
        buttons = new JButton[9];
        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.white);
            panelButtons.add(buttons[i]);
            buttons[i].addActionListener(new Buttonlistener());
        }
        buttons[0].setIcon(X);
        buttons[0].setIcon(O);
        buttons[1].setIcon(X);
        buttons[5].setIcon(O);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        panel.add(panelButtons);
        
        this.add(panel);
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

    private void setMenu() {
        menu = new JMenu("Menü");
        newGame = new JMenuItem("Neues Spiel");
        modePlayer = new JMenuItem("Modus/Player Auswahl");
        change = new JMenuItem("x-o Tausch");
        close = new JMenuItem("Spiel beenden");
        
        menu.add(newGame);
        menu.add(modePlayer);
        menu.add(change);
        menu.add(close);
        JMenuBar mBar = new JMenuBar();
        mBar.add(menu);
        this.setJMenuBar(mBar);
        
        newGame.addActionListener(new newGameListener());
        close.addActionListener(new closeListener());
        modePlayer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new ModePlayerSwing(master);
    }

    private static class Buttonlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        
        }

    }
    
    private class closeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(
            getParent(),
            "Are you sure you want to exit the application?",
            "Exit Application",
            JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION)
            {
        	System.exit(0);
            }
        }
    }
    
    private class newGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < 9; i++) {
                buttons[i].setIcon(null);
            }
        }
    }
}
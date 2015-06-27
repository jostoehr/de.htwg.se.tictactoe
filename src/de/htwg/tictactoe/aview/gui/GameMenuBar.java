package de.htwg.tictactoe.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Johannes Stöhr
 */
public class GameMenuBar extends JMenuBar {
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem modePlayer;
    private JMenuItem change;
    private JMenuItem close;
    
    public GameMenuBar() {
        
        menu = new JMenu("Menü");
        newGame = new JMenuItem("Neues Spiel");
        modePlayer = new JMenuItem("Modus/Player Auswahl");
        change = new JMenuItem("x-o Tausch");
        close = new JMenuItem("Spiel beenden");
        
        menu.add(newGame);
        menu.add(modePlayer);
        menu.add(change);
        menu.add(close);
        this.add(menu);
        
        close.addActionListener(new closeListener());
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
        	System.exit(0);    
        }
    }
}

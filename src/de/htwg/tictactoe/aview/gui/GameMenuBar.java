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
    
    public GameMenuBar() {
        
        JMenu menu = new JMenu("Menü");
        JMenuItem newGame = new JMenuItem("Neues Spiel");
        JMenuItem modePlayer = new JMenuItem("Modus/Player Auswahl");
        JMenuItem change = new JMenuItem("x-o Tausch");
        JMenuItem close = new JMenuItem("Spiel beenden");
        
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
            {
        	System.exit(0);
            }
        }
    }
}

package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.util.Value;
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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;

/**
 *
 * @author Johannes Stöhr
 */
public class GUI extends JFrame implements IObserver {
    
    private final MasterController master;
    
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem modePlayer;
    private JMenuItem change;
    private JMenuItem close;
    
    private JButton buttons[][];
    
    ImageIcon X,O,iconOkay;
    
    @Inject
    public GUI(MasterController master) {
        master.addObserver(this);
        this.master = master;
        master.init();
        master.setCurrentState(State.STATECROSSPLAYING);
        
        setTitle("TicTacToe");
        URL iconURL = getClass().getResource("icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setMenu();
        
        X = new ImageIcon(this.getClass().getResource("cross2.png"));
        O = new ImageIcon(this.getClass().getResource("nought.png"));
        iconOkay = new ImageIcon(this.getClass().getResource("okay.png"));
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(3,1));
        buttons = new JButton[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
            buttons[i][j] = new JButton();
            buttons[i][j].setBackground(Color.white);
            panelButtons.add(buttons[i][j]);
            buttons[i][j].addActionListener(new Buttonlistener());
            }
        }
        
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
        
        newGame.addActionListener(new menuListener());
        close.addActionListener(new menuListener());
        modePlayer.addActionListener(new menuListener());
        change.addActionListener(new menuListener());
    }
    
    private void newGame() {
        master.init();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setIcon(null);
            }
        }
        master.setCurrentState(State.STATECROSSPLAYING);
    }

    private class Buttonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            ImageIcon value;
            if(master.win().equals("playing")) {
                if(master.getCurrentState() == State.STATECROSSPLAYING) {
                    value = X;
                } else {
                    value = O;
                }
                
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(source == buttons[i][j]) {
                            if(buttons[i][j].getIcon() == null) {
                                buttons[i][j].setIcon(value);
                                master.setValue(i, j);
                            }
                        }
                    }
                }
                master.change();
            } else {
                showMessageDialog(null, "Starten Sie zuerst ein neues Spiel!", "Spielneustart erforderlich!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class menuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            
            switch(source) {
                case "Neues Spiel":
                    newGame();
                    break;
                case "x-o Tausch":
                    if(!master.isEmpty()) {
                        showMessageDialog(null, "Starten Sie zuerst ein neues Spiel!", "Spielneustart erforderlich!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if(master.getPlayer1().getCharacter().equals(Value.CROSS)) {
                            master.getPlayer1().setCharacter(Value.NOUGHT);
                            master.getPlayer2().setCharacter(Value.CROSS);
                        } else {
                            master.getPlayer1().setCharacter(Value.CROSS);
                            master.getPlayer2().setCharacter(Value.NOUGHT);                
                        }
                        showMessageDialog(null, "Charakter X und O getauscht", "Player getauscht", JOptionPane.INFORMATION_MESSAGE, iconOkay);

                        /*if(mode == 1 && (controller.getPlayer2().getCharacter() == Value.CROSS)) {
                            setCellAI();
                        }*/
                    }
                    break;
                case "Modus/Player Auswahl":
                    dispose();
                    new ModePlayer(master);
                    newGame();
                    break;
                case "Spiel beenden":
                    int result = JOptionPane.showConfirmDialog(
                    getParent(),
                    "Are you sure you want to exit the application?",
                    "Exit Application",
                    JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}
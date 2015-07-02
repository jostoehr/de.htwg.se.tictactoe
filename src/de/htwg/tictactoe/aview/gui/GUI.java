package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import static de.htwg.tictactoe.model.impl.Grid.ROWS;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.util.Value;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private static final int HEIGHT = 800;
    private JLabel win1;
    private JLabel lost1;
    private JLabel draw1;
    private JLabel win2;
    private JLabel lost2;
    private JLabel draw2;
    
    private static final String spielvorbei = "Spiel vorbei";
    private static final String hatgewonnen = " hat gewonnen!";
    
    private JButton buttons[][];
    
    private ImageIcon x,o,iconOkay;
    
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
        
        x = new ImageIcon(this.getClass().getResource("cross2.png"));
        o = new ImageIcon(this.getClass().getResource("nought.png"));
        iconOkay = new ImageIcon(this.getClass().getResource("okay.png"));
        
        JLabel player1 = new JLabel(master.getPlayer1().getName());
        player1.setFont(player1.getFont().deriveFont(Font.BOLD));
        JLabel player2 = new JLabel(master.getPlayer2().getName());
        player2.setFont(player2.getFont().deriveFont(Font.BOLD));
        win1 = new JLabel("Gewonnen: 0");
        lost1 = new JLabel("Verloren: 0");
        draw1 = new JLabel("Unentschieden: 0");
        win2 = new JLabel("Gewonnen: 0");
        lost2 = new JLabel("Verloren: 0");
        draw2 = new JLabel("Unentschieden: 0");
        
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
        
        JPanel panelStats = new JPanel();
        panelStats.setLayout(new GridLayout(4,2));
        panelStats.add(player1);
        panelStats.add(player2);
        panelStats.add(win1);
        panelStats.add(win2);
        panelStats.add(lost1);
        panelStats.add(lost2);
        panelStats.add(draw1);
        panelStats.add(draw2);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(panelButtons, BorderLayout.CENTER);
        panel.add(panelStats, BorderLayout.SOUTH);

        this.add(panel);
        pack();
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        
        Toolkit tk = getToolkit();
        
        setLocation((tk.getScreenSize().width-this.getWidth())/2,
                    (tk.getScreenSize().height-this.getHeight())/2);
    }

    private void setMenu() {
        JMenu menu = new JMenu("Menü");
        JMenuItem newGame = new JMenuItem("Neues Spiel");
        JMenuItem modePlayer = new JMenuItem("Modus/Player Auswahl");
        JMenuItem change = new JMenuItem("x-o Tausch");
        JMenuItem close = new JMenuItem("Spiel beenden");
        
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
    
    private void setCellAI() {
        List<String> l = master.getUnSetCells();
        int random = master.randInt(0, l.size() - 1);
        int arg0 = Integer.parseInt(String.valueOf(l.get(random).charAt(0)));
        int arg1 = Integer.parseInt(String.valueOf(l.get(random).charAt(1)));                
        ImageIcon value;
        if(master.getCurrentState() == State.STATECROSSPLAYING) {
                    value = x;
                } else {
                    value = o;
                }
        buttons[arg0][arg1].setIcon(value);
        master.setValue(arg0, arg1);
    }
    
    private void newGame() {
        master.init();
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < ROWS; j++) {
                buttons[i][j].setIcon(null);
            }
        }
        master.setCurrentState(State.STATECROSSPLAYING);
        if(master.getMode() == 1 && (master.getPlayer2().getCharacter() == Value.CROSS)) {
            setCellAI();
        }
    }

    @Override
    public void update(Event e) {
        
    }
    
    private void updateStatistic() {
        if(master.getCurrentState() == State.STATECROSSWON || 
                master.getCurrentState() == State.STATENOUGHTWON ||
                master.getCurrentState() == State.STATEDRAW) {
            win1.setText("Gewonnen: " + master.getPlayer1().getWinCount());
            lost1.setText("Verloren: " + master.getPlayer1().getLostCount());
            draw1.setText("Unentschieden: " + master.getPlayer1().getDrawCount());
            win2.setText("Gewonnen: " + master.getPlayer2().getWinCount());
            lost2.setText("Verloren: " + master.getPlayer2().getLostCount());
            draw2.setText("Unentschieden: " + master.getPlayer2().getDrawCount());
        }
    }
    
    private void checkGameWin() {
        updateStatistic();
        if(master.getCurrentState() == State.STATECROSSWON) {
            if(master.win().equals(master.getPlayer1().getName())) {
                showMessageDialog(null, master.getPlayer1().getCharacter() + hatgewonnen, spielvorbei, JOptionPane.INFORMATION_MESSAGE, iconOkay);
            } else {
                showMessageDialog(null, master.getPlayer2().getCharacter() + hatgewonnen, spielvorbei, JOptionPane.INFORMATION_MESSAGE, iconOkay);
            }
        }
        if(master.getCurrentState() == State.STATENOUGHTWON) {
            if(master.win().equals(master.getPlayer1().getName())) {
                showMessageDialog(null, master.getPlayer1().getCharacter() + hatgewonnen, spielvorbei, JOptionPane.INFORMATION_MESSAGE, iconOkay);
            } else {
                showMessageDialog(null, master.getPlayer2().getCharacter() + hatgewonnen, spielvorbei, JOptionPane.INFORMATION_MESSAGE, iconOkay);
            }
        }
        if(master.getCurrentState() == State.STATEDRAW) {
            showMessageDialog(null, "Unentschieden!", "Spiel vorbei", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class Buttonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            ImageIcon value;
            if(master.win().equals("playing")) {
                if(master.getCurrentState() == State.STATECROSSPLAYING) {
                    value = x;
                } else {
                    value = o;
                }
                
                for(int i = 0; i < ROWS; i++) {
                    for(int j = 0; j < ROWS; j++) {
                        if(source == buttons[i][j]) {
                            if(buttons[i][j].getIcon() == null) {
                                buttons[i][j].setIcon(value);
                                master.setValue(i, j);
                            }
                        }
                    }
                }
                checkGameWin();
                if(master.win().equals("playing") &&
                    master.getMode() == 1 &&
                    master.getCurrentPlayer().getName().equals("AIntelligence")) {
                        setCellAI();
                        checkGameWin();
                }
            } else {
                showMessageDialog(null, "Spiel zu Ende, startet Sie ein neues Spiel!", "Spielneustart erforderlich!", JOptionPane.ERROR_MESSAGE);
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
                        if(master.getMode() == 1 && (master.getPlayer2().getCharacter() == Value.CROSS)) {
                            setCellAI();
                        }
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
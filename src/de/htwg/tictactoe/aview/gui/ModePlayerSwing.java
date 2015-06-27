package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.util.Value;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.PAGE_START;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author johannes
 */
public class ModePlayerSwing extends JFrame implements ActionListener {
    
    private MasterController master;
    
    private JRadioButton aintelligence;
    private JRadioButton player;
    private JLabel Lbltop;
    private JLabel LblmodeTitle;
    private JLabel LblplayerTitle1;
    private JLabel LblplayerTitle2;
    private JLabel Lblplayer1;
    private JLabel Lblplayer2;
    private JTextField txtBoxplayer1;
    private JTextField txtBoxplayer2;
    private JButton reset;
    private JButton apply;    
    private ButtonGroup group;
    private static float ROT = 20;
    private static float GRUEN = 100;
    private static float BLAU = 150;
    private int TEXTFIELDCOLUMN = 18;
    private int WIDTH = 700;
    private int HEIGHT = 250;
    
    private static Color bluecolor = new Color(ROT , GRUEN, BLAU);
    @Inject
    public ModePlayerSwing(MasterController master) {
        this.master = master;
        
        setTitle("Bitte Auswählen...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        aintelligence = new JRadioButton("Gegen KI spielen");
        aintelligence.setBackground(Color.white);
        aintelligence.setSelected(true);
        player = new JRadioButton("Gegen Spieler spielen");
        player.setBackground(Color.white);
        Lbltop = new JLabel("Auswahl treffen:");
        Lbltop.setForeground(Color.white);
        Lblplayer1 = new JLabel("Spielername 1: ");
        Lblplayer2 = new JLabel("Spielername 2: ");
        LblmodeTitle = new JLabel("Wählen Sie einen Modus: ");
        LblplayerTitle1 = new JLabel("Geben Sie Ihren Namen ein: ");
        LblplayerTitle2 = new JLabel("Geben Sie Ihren Namen ein: ");
        txtBoxplayer1 = new JTextField("", TEXTFIELDCOLUMN);
        txtBoxplayer2 = new JTextField("", TEXTFIELDCOLUMN);
        reset = new JButton("Zurücksetzen");
        apply = new JButton("Übernehmen");
        
        group = new ButtonGroup();
        group.add(aintelligence);
        group.add(player);
        
        intelligence();
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel1.setBackground(Color.white);
        panel1.add(LblmodeTitle);
        panel1.add(aintelligence);
        panel1.add(player);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.white);
        panel2.add(Lblplayer1);
        panel2.add(txtBoxplayer1);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(Color.white);
        panel3.add(Lblplayer2);
        panel3.add(txtBoxplayer2);
                
        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(4,1));
        panel4.setBackground(Color.white);
        panel4.add(LblplayerTitle1);
        panel4.add(panel2);
        panel4.add(LblplayerTitle2);
        panel4.add(panel3);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1,2));
        panel5.add(reset);
        panel5.add(apply);
        
        JPanel panel6 = new JPanel();
        panel6.add(Lbltop);
        panel6.setBackground(bluecolor);
        
        JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayout(1,2));
        panel7.add(panel1);
        panel7.add(panel4);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(panel6, BorderLayout.PAGE_START);
        panel.add(panel7, BorderLayout.CENTER);
        panel.add(panel5, BorderLayout.PAGE_END);
        panel.setBackground(bluecolor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        setContentPane(panel);
        
        pack();
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        Toolkit tk = getToolkit();
        setLocation((tk.getScreenSize().width-this.getWidth())/2,
                    (tk.getScreenSize().height-this.getHeight())/2);
        
        aintelligence.addActionListener(this);
        player.addActionListener(this);
        reset.addActionListener(this);
        apply.addActionListener(this);
    }
    
    public void intelligence(){
        LblplayerTitle2.setVisible(false);
        Lblplayer2.setVisible(false);
        txtBoxplayer2.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(aintelligence.isSelected()) {
            intelligence();
        } 
        if(player.isSelected()) {
            LblplayerTitle2.setVisible(true);
            Lblplayer2.setVisible(true);
            txtBoxplayer2.setVisible(true);
        }
        
        if(e.getSource() == apply) {
            if(aintelligence.isSelected()) {
                if(txtBoxplayer1.getText().equals("")) { 
                    showMessageDialog(null, "Bitte einen Namen wählen!");
                    return;
                } else {
                    master.setPlayer1(txtBoxplayer1.getText(), Value.CROSS);
                    master.setPlayer2("AIntelligence", Value.NOUGHT);
                    
                }
            } 
            if(player.isSelected()) {
                if(txtBoxplayer1.getText().equals("") || 
                        txtBoxplayer2.getText().equals("")) {
                    showMessageDialog(null, "Bitte einen Namen wählen!");
                    return;
                } else {
                    master.setPlayer1(txtBoxplayer1.getText(), Value.CROSS);
                    master.setPlayer2(txtBoxplayer2.getText(), Value.NOUGHT);
                    
                }
            }
            showMessageDialog(null, master.getPlayer1().getName() + " ist x\n"
                              + master.getPlayer2().getName() + " ist o");
            this.dispose();
            new GUISwing(this.master);
        }
        
        if(e.getSource() == reset) {
            intelligence();
            txtBoxplayer1.setText("");
            txtBoxplayer2.setText("");
        }
    }
}

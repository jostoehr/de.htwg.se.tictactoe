package de.htwg.tictactoe.aview.gui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.util.Value;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author johannes
 */
public class ModePlayer extends JFrame implements ActionListener {
    
    private MasterController master;
    
    private JRadioButton aintelligence;
    private JRadioButton player;
    private JLabel lblPlayerTitle2;
    private JLabel lblPlayer2;
    private JTextField txtBoxplayer1;
    private JTextField txtBoxplayer2;
    private JButton reset;
    private JButton apply;
    private static final int ROT = 20;
    private static final int GRUEN = 100;
    private static final int BLAU = 150;
    private static final int TEXTFIELDCOLUMN = 18;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 250;
    private static final int ROWPANELONE = 3;
    private static final int ROWPANELFOUR = 4;
    private static final int ROWPANELFIFEANDSEVEN = 1;
    private static final int COLPANELFIFEANDSEVEN = 2;
    private static final int COLPANEL = 1;
    private static final int TOP = 10;
    private static final int LEFTANDRIGHT = 0;
    private static final int BOTTOM = 20;
    private static final int HALF = 2;
    
    
    
    private  Color bluecolor = new Color(ROT , GRUEN, BLAU);
    @Inject
    public ModePlayer(MasterController master) {
        this.master = master;
        
        setTitle("Bitte Auswählen...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        aintelligence = new JRadioButton("Gegen KI spielen");
        aintelligence.setBackground(Color.white);
        aintelligence.setSelected(true);
        player = new JRadioButton("Gegen Spieler spielen");
        player.setBackground(Color.white);
        
        JLabel lblTop = new JLabel("Auswahl treffen:");
        lblTop.setForeground(Color.white);
        JLabel lblPlayer1 = new JLabel("Spielername 1: ");
        lblPlayer2 = new JLabel("Spielername 2: ");
        JLabel lblModeTitle = new JLabel("Wählen Sie einen Modus: ");
        JLabel lblPlayerTitle1 = new JLabel("Geben Sie Ihren Namen ein: ");
        lblPlayerTitle2 = new JLabel("Geben Sie Ihren Namen ein: ");
        txtBoxplayer1 = new JTextField("", TEXTFIELDCOLUMN);
        txtBoxplayer2 = new JTextField("", TEXTFIELDCOLUMN);
        reset = new JButton("Zurücksetzen");
        apply = new JButton("Übernehmen");
        
        ButtonGroup group = new ButtonGroup();
        group.add(aintelligence);
        group.add(player);
        URL iconURL = getClass().getResource("icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        intelligence();
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(ROWPANELONE,COLPANEL));
        panel1.setBackground(Color.white);
        panel1.add(lblModeTitle);
        panel1.add(aintelligence);
        panel1.add(player);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.white);
        panel2.add(lblPlayer1);
        panel2.add(txtBoxplayer1);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(Color.white);
        panel3.add(lblPlayer2);
        panel3.add(txtBoxplayer2);
                
        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(ROWPANELFOUR,COLPANEL));
        panel4.setBackground(Color.white);
        panel4.add(lblPlayerTitle1);
        panel4.add(panel2);
        panel4.add(lblPlayerTitle2);
        panel4.add(panel3);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(ROWPANELFIFEANDSEVEN,COLPANELFIFEANDSEVEN));
        panel5.add(reset);
        panel5.add(apply);
        
        JPanel panel6 = new JPanel();
        panel6.add(lblTop);
        panel6.setBackground(bluecolor);
        
        JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayout(ROWPANELFIFEANDSEVEN,COLPANELFIFEANDSEVEN));
        panel7.add(panel1);
        panel7.add(panel4);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(panel6, BorderLayout.PAGE_START);
        panel.add(panel7, BorderLayout.CENTER);
        panel.add(panel5, BorderLayout.PAGE_END);
        panel.setBackground(bluecolor);
        panel.setBorder(BorderFactory.createEmptyBorder(TOP, LEFTANDRIGHT, BOTTOM, LEFTANDRIGHT));
        setContentPane(panel);
        
        pack();
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        Toolkit tk = getToolkit();
        setLocation((tk.getScreenSize().width-this.getWidth())/HALF,
                    (tk.getScreenSize().height-this.getHeight())/HALF);
        
        aintelligence.addActionListener(this);
        player.addActionListener(this);
        reset.addActionListener(this);
        apply.addActionListener(this);
    }
    
    private void intelligence(){
        lblPlayerTitle2.setVisible(false);
        lblPlayer2.setVisible(false);
        txtBoxplayer2.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        URL iconURL = getClass().getResource("okay.png");
        ImageIcon iconOkay = new ImageIcon(iconURL);
        if(aintelligence.isSelected()) {
            intelligence();
        } 
        if(player.isSelected()) {
            lblPlayerTitle2.setVisible(true);
            lblPlayer2.setVisible(true);
            txtBoxplayer2.setVisible(true);
        }
        
        if(e.getSource() == apply) {
            if(aintelligence.isSelected()) {
                if(txtBoxplayer1.getText().equals("")) { 
                    showMessageDialog(null, "Bitte geben Sie einen Namen ein!","Name fehlt!", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    master.setPlayer1(txtBoxplayer1.getText(), Value.CROSS);
                    master.setPlayer2("AIntelligence", Value.NOUGHT);
                    
                }
            } 
            if(player.isSelected()) {
                if(txtBoxplayer1.getText().equals("") || 
                        txtBoxplayer2.getText().equals("")) {
                    showMessageDialog(null, "Bitte geben Sie einen Namen ein!","Name fehlt!", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    master.setPlayer1(txtBoxplayer1.getText(), Value.CROSS);
                    master.setPlayer2(txtBoxplayer2.getText(), Value.NOUGHT);
                    
                }
            }
            showMessageDialog(null, master.getPlayer1().getName() + " ist x\n"
                              + master.getPlayer2().getName() + " ist o", "Player gesetzt", JOptionPane.INFORMATION_MESSAGE, iconOkay);
            this.dispose();
            GUI guiSwing = new GUI(this.master);
        }
        
        if(e.getSource() == reset) {
            intelligence();
            txtBoxplayer1.setText("");
            txtBoxplayer2.setText("");
        }
    }
}

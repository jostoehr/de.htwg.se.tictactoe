package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.util.observer.IObserver;
import de.htwg.tictactoe.controller.TictactoeController;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import de.htwg.tictactoe.entities.Enum;
import de.htwg.tictactoe.entities.Player;

/**
 *
 * @author johannes
 */
public class TextUI implements IObserver {

    private TictactoeController controller;
    private PlayerController pcontroller;
    private Player player1;
    private Player player2;
    Scanner scanner;
    
    public TextUI(TictactoeController controller, PlayerController pcontroller) {
        this.controller = controller;
        this.pcontroller = pcontroller;
        scanner = new Scanner(System.in);
        player();        
    }
    
    
    
    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        System.out.println(controller.getGridString());
	System.out.println(controller.getStatus());

        System.out
                .println("Please enter a command( q - quit, n - new, "
                        + " xyz - set cell(x,y) and z 0 = x or 1 = o)");
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("q")) {
            continu = false;
        } 
        if (line.equalsIgnoreCase("n")) {
            
        }
        if (line.matches("[0-9][0-9][0-1]")){
            int[] arg = readToArray(line);
            Enum value;
            if(arg[2] == 0){
                value = Enum.CROSS;
            } else {
                value = Enum.NOUGHT;
            }
            controller.setValue(arg[0], arg[1], value);
        }
        return continu;
    }
    private int[] readToArray(String line) {
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(line);
		int[] arg = new int[line.length()];
		for (int i = 0; i < arg.length; i++) {
			m.find();
			arg[i] = Integer.parseInt(m.group());
		}
		return arg;
	}

    private void player() {
        String playername[] = new String[2];
        for(int i = 0; i < 2; i++) {
            System.out.println("Type in Playername" + (i + 1) + ": ");
            scanner = new Scanner(System.in);
            playername[i] = scanner.next();
        }
        pcontroller.setPlayer1(playername[0], Enum.CROSS);
        pcontroller.setPlayer2(playername[1], Enum.NOUGHT);
        player1 = pcontroller.getPlayer1();
        player2 = pcontroller.getPlayer2();
    } 
}

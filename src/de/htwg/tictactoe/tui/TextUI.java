package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.entities.Enum;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.entities.Player;
import de.htwg.util.observer.IObserver;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author johannes
 */
public class TextUI implements IObserver {

    private TictactoeController controller;
    private PlayerController pcontroller;
    private Grid grid;
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
        printHelp();
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("h")) {
            printHelp();
        } 
        if (line.equalsIgnoreCase("n")) {
            grid = new Grid();
            controller = new TictactoeController(grid);
            controller.addObserver(this);
        }
        if (line.equalsIgnoreCase("s")) {
            if (line.matches("[0-9][0-9][ox]")){
                int[] arg = readToArray(line);
                Enum value;
                if(arg[2] == 'x'){
                    value = Enum.CROSS;
                } else {
                    value = Enum.NOUGHT;
                } 
                controller.setValue(arg[0], arg[1], value);
            }
        }
        
        if (line.equalsIgnoreCase("q")) { 
            continu = false;
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
    
    private void printHelp() {
        System.out.println("\n-----MENU-----\nh\t-\tHelp\nn\t-\tNew Game\n"
                + "s\t-\tSet Cell((x,y) = x or 0) like 1 2 x\n"
                + "q\t-\tquit");
    }
}

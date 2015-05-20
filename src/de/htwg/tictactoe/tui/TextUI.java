package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.impl.StateCrossPlaying;
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
    private Grid grid;
    private Scanner scanner;
    
    public TextUI(TictactoeController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        controller.player();        
    }
    
    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        System.out.print(controller.getGridString());
	System.out.print(controller.getStatus());
        printHelp();
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("h")) {
            printHelp();
        } 
        if (line.equalsIgnoreCase("n")) {
            controller.init();
            System.out.println("New Game is created");
            controller.setCurrentState(new StateCrossPlaying(controller));
            System.out.println(controller.getPlayer1().getName() + " it's your turn!");
        }
        
        /**if (line.equalsIgnoreCase("u"))
            controller.test();
        */
        
        if (line.equalsIgnoreCase("s")) {
            if(controller.win().equals("playing")) {
                String line2;
                line2 = scanner.next();
                if (line2.matches("[0-9][0-9]")){
                    int[] arg = readToArray(line2);
                    controller.setValue(arg[0], arg[1]);
                } 
            } else {
                System.out.println("Game is over, press 'n' to restart");
            }
            checkGame();
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


    
    private void printHelp() {
        System.out.print("\n-----MENU-----\nh\t-\tHelp\nn\t-\tNew Game\n"
                + "s\t-\tSet Cell((x,y) like: 10\n"
                + "q\t-\tquit\n");
    }

    private void checkGame() {
        String win = controller.win();
        if(win.equals(controller.getPlayer1().getName())) {
            System.out.println(controller.getPlayer1().getCharacter() + " wins!"
                    + " Press 'n' to restart");
        } else if(win.equals(controller.getPlayer2().getName())) {
            System.out.println(controller.getPlayer2().getCharacter() + " wins!"
                    + " Press 'n' to restart");
        } else if(win.equals("draw")) {
            System.out.println("Game is Draw! Press 'n' to restart");
        } else if(win.equals("playing")) {
            System.out.println("Game is not finished!");
        }
    }
}

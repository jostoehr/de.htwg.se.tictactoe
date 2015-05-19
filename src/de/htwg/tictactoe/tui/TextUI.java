package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.WinController;
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
    private WinController wcontroller;
    private Grid grid;
    private Scanner scanner;
    private Player player1;
    private Player player2;
    
    public TextUI(TictactoeController controller, PlayerController pcontroller, WinController wcontroller) {
        this.controller = controller;
        this.pcontroller = pcontroller;
        this.wcontroller = wcontroller;
        scanner = new Scanner(System.in);
        player();        
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
            grid = new Grid();
            controller = new TictactoeController(grid);
            controller.addObserver(this);
            wcontroller = new WinController(grid, player1, player2);
            wcontroller.addObserver(this);
            System.out.println("New Game is created");
            System.out.println(player1.getName() + " it's your turn!");
        }
        if (line.equalsIgnoreCase("s")) {
            if(wcontroller.win().equals("playing")) {    
                line = scanner.next();
                if (line.matches("[0-9][0-9][0-1]")){
                    int[] arg = readToArray(line);
                    Enum value;
                    if(arg[2] == 0) {
                        value = Enum.CROSS;
                    } else {
                        value = Enum.NOUGHT;
                    } 
                    controller.setValue(arg[0], arg[1], value);
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

    private void player() {
        String playername[] = new String[2];
        for(int i = 0; i < 2; i++) {
            System.out.println("Type in Playername" + (i + 1) + ": ");
            playername[i] = scanner.next();
        }
        pcontroller.setPlayer1(playername[0], Enum.CROSS);
        pcontroller.setPlayer2(playername[1], Enum.NOUGHT);
        Player player1 = pcontroller.getPlayer1();
        this.player1 = player1;
        Player player2 = pcontroller.getPlayer2();
        this.player2 = player2;
        System.out.println(player1.getName() + " is " + player1.getCharacter() 
                + ", " + player2.getName() + " is " + player2.getCharacter());
    }
    
    private void printHelp() {
        System.out.print("\n-----MENU-----\nh\t-\tHelp\nn\t-\tNew Game\n"
                + "s\t-\tSet Cell((x,y) = 0 for x or 1 for o) like 1 2 0\n"
                + "q\t-\tquit\n");
    }

    private void checkGame() {
        String win = wcontroller.win();
        if(win.equals(player1.getName()))
            System.out.println(player1.getCharacter() + " wins!"
                    + " Press 'n' to restart");
        else if(win.equals(player2.getName()))
            System.out.println(player2.getCharacter() + " wins!"
                    + " Press 'n' to restart");
        else if(win.equals("draw")) {
            System.out.println("Game is Draw! Press 'n' to restart");
        } else if(win.equals("playing")) {
            System.out.println("Game is not finished!");
        }
    }
}

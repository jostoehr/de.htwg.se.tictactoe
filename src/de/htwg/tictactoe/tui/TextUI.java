package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.impl.State;
import de.htwg.tictactoe.entities.Value;
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
    private Scanner scanner;
    private int mode;
    
    /**
     * String literal for printStatus
     */
    private final String turn = " it's your turn!";
    
    /**
     * String literal is
     */
    private final String is = " is ";
    
    /**
     * String literal -->
     */
    private final String in = "--> "; 
    
    public TextUI(TictactoeController controller) {
        this.controller = controller;
        controller.init();
        controller.setCurrentState(State.StateCrossPlaying);
        scanner = new Scanner(System.in);
        modeChange();
        player();        
    }
    
    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        printStatus();
        System.out.print(controller.getGridString());
	System.out.print(controller.getStatus());
        printHelp();
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("h")) {
            printIndexHelp();
        } 
        if (line.equalsIgnoreCase("n")) {
            controller.init();
            System.out.println("New Game is created");
            controller.setCurrentState(State.StateCrossPlaying);
        }
        
        if (line.equalsIgnoreCase("m")) {
            modeChange();
            player();
            controller.init();
            controller.setCurrentState(State.StateCrossPlaying);
        }
        
        if (line.equalsIgnoreCase("c")) {
            if(controller.getCurrentState() == State.StateCrossWon
                    || controller.getCurrentState() == State.StateNoughtWon
                    || controller.getCurrentState() == State.StateDraw 
                    || controller.isEmpty()) {
                if(controller.getPlayer1().getCharacter().equals(Value.CROSS)) {
                    controller.getPlayer1().setCharacter(Value.NOUGHT);
                    controller.getPlayer2().setCharacter(Value.CROSS);
                } else {
                    controller.getPlayer1().setCharacter(Value.CROSS);
                    controller.getPlayer2().setCharacter(Value.NOUGHT);                
                }
                System.out.println("Changed Characters x and o");
                System.out.println(controller.getPlayer1().getName() + is + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + is + controller.getPlayer2().getCharacter());
            } else {
                System.out.println("Can't change Cross and Nought while playing!");
            }
        }
        
        if (line.equalsIgnoreCase("s")) {
            if(controller.win().equals("playing")) {
                String line2;
                do {
                    System.out.print("Type in the Cell you want to set "
                        + "(example: 00, choose 'h' to get a Indizes Help)\n" + in);
                    line2 = scanner.next();
                    if (line2.equalsIgnoreCase("h")) {
                        printIndexHelp();
                        System.out.print("Type in the Cell you want to set\n"
                            + in);
                        line2 = scanner.next();
                    } 
                    if (line2.matches("[0-2][0-2]")){
                        int[] arg = readToArray(line2);
                        controller.setValue(arg[0], arg[1]);
                    } 
                } while(!line2.matches("[0-2][0-2]")); 
            } else {
                System.out.println("Game is over, press 'n' to restart");
            }
            checkGame();
        }
        
        if (line.equalsIgnoreCase("p")) {
            printStatistic();
        }
        if (line.equalsIgnoreCase("q")) { 
            continu = false;
        }
        return continu;
    }
    private int[] readToArray(String line) {
		Pattern p = Pattern.compile("[0-2]");
		Matcher m = p.matcher(line);
		int[] arg = new int[line.length()];
		for (int i = 0; i < arg.length; i++) {
			m.find();
			arg[i] = Integer.parseInt(m.group());
		}
		return arg;
	}
    
    private void printHelp() {
        System.out.print("\n-----MENU-----\n"
                + "h\t-\tMatrix Indizes Help\n"
                + "n\t-\tNew Game\n"
                + "m\t-\tGame Mode\n"
                + "c\t-\tChange Player1 to o, Player2 to x\n"
                + "s\t-\tSet Cell((x,y) like: 10\n"
                + "p\t-\tprint Statistics\n"
                + "q\t-\tquit\n");
        System.out.print(in);
    }
    
    private void printIndexHelp() {
        System.out.println("Matrix Indizes to set a cell:\n\n"
            + "00 | 01 | 02\n"
            + "------------\n"
            + "10 | 11 | 12\n"
            + "------------\n"
            + "20 | 21 | 22\n");
    }
    
    private void printStatus() {
        if(controller.getCurrentState() == State.StateCrossPlaying ||
                controller.getCurrentState() == State.StateNoughtPlaying) {
            System.out.println(controller.getCurrentPlayer().getName() + turn);
        }
    }
    
    private void printStatistic() {
        System.out.println("\n" + controller.getPlayer1().getName() + "(" 
                + controller.getPlayer1().getCharacter() + ")\t" 
                + controller.getPlayer2().getName() + "(" 
                + controller.getPlayer2().getCharacter() + ")");
        System.out.println("Wins: " + controller.getPlayer1().getWinCount()
                + "\t\tWins: " + controller.getPlayer2().getWinCount());
        System.out.println("Lost: " + controller.getPlayer1().getLooseCount()
                + "\t\tLost: " + controller.getPlayer2().getLooseCount());
        System.out.println("Draw: " + controller.getPlayer1().getDrawCount()
                + "\t\tDraw: " + controller.getPlayer2().getDrawCount() + "\n");
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
    
    private void player() {
        String playername[] = new String[mode];
        for (int i = 0; i < mode; i++) {
            System.out.print("Type in Playername" + (i + 1) + ":\n"
                    + in);
            playername[i] = scanner.next();
        }
        controller.getPlayer1().setName(playername[0]);
        if(mode == 2) {
            controller.getPlayer2().setName(playername[1]);
            System.out.println(controller.getPlayer1().getName() + is + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + is + controller.getPlayer2().getCharacter());
        } else {
            controller.getPlayer2().setName("Artificial Intelligence");
            System.out.println(controller.getPlayer1().getName() + is + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + is + controller.getPlayer2().getCharacter());
        }
    }
    
    private void modeChange() {
        while(true) {
            System.out.print("Choose your game Mode:\n"
                + "1\t-\tvs Artificial Intelligence\n"
                + "2\t-\tvs Player\n"
                + in);
               
            if(scanner.hasNextInt()) {
                mode = scanner.nextInt();
            } else {
                scanner.next();
                continue;
            }
            if(mode == 1) {
            this.mode = mode;
                break;
            } else if(mode == 2) {
                this.mode = mode;            
                break;
            }    
        }
    }
}

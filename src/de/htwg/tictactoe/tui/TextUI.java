package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.impl.State;
import de.htwg.tictactoe.entities.Value;
import de.htwg.util.observer.IObserver;
import java.util.List;
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
    private static final String TURN = " it's your turn!";
    
    /**
     * String literal is
     */
    private static final String IS = " is ";
    
    /**
     * String literal -->
     */
    private static final String IN = "--> "; 
    
    public TextUI(TictactoeController controller) {
        this.controller = controller;
        controller.init();
        controller.setCurrentState(State.STATECROSSPLAYING);
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
            controller.setCurrentState(State.STATECROSSPLAYING);
            if(mode == 1 && (controller.getPlayer2().getCharacter() == Value.CROSS)) {
                setCellAI();
            }
        }
        
        if (line.equalsIgnoreCase("m")) {
            modeChange();
            player();
            controller.init();
            controller.setCurrentState(State.STATECROSSPLAYING);
        }
        
        if (line.equalsIgnoreCase("c")) {
            if(!controller.isEmpty()) {
                System.out.println("Start first a new game to change Characters");
            } else {
                if(controller.getPlayer1().getCharacter().equals(Value.CROSS)) {
                    controller.getPlayer1().setCharacter(Value.NOUGHT);
                    controller.getPlayer2().setCharacter(Value.CROSS);
                } else {
                    controller.getPlayer1().setCharacter(Value.CROSS);
                    controller.getPlayer2().setCharacter(Value.NOUGHT);                
                }
                System.out.println("Changed Characters x and o");
                System.out.println(controller.getPlayer1().getName() + IS + controller.getPlayer1().getCharacter()
                    + ", " + controller.getPlayer2().getName() + IS + controller.getPlayer2().getCharacter());
                if(mode == 1 && (controller.getPlayer2().getCharacter() == Value.CROSS)) {
                    setCellAI();
                }
            }
        }
        
        if (line.equalsIgnoreCase("s")) {
            if(controller.win().equals("playing")) {
                String line2;
                do {
                    System.out.print("Type in the Cell you want to set "
                        + "(example: 00, choose 'h' to get a Indizes Help)\n" + IN);
                    line2 = scanner.next();
                    if (line2.equalsIgnoreCase("h")) {
                        printIndexHelp();
                        System.out.print("Type in the Cell you want to set\n"
                            + IN);
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
            if(!checkGameEnd()) {
                if(mode == 1 && controller.getCurrentPlayer()
                        .getName().equals("Artificial Intelligence")) {
                    setCellAI();
                }
            }
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
        System.out.print(IN);
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
        if(controller.getCurrentState() == State.STATECROSSPLAYING ||
                controller.getCurrentState() == State.STATENOUGHTPLAYING) {
            System.out.println(controller.getCurrentPlayer().getName() + TURN);
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

    private boolean checkGameEnd() {
        String win = controller.win();
        if(win.equals(controller.getPlayer1().getName())) {
            System.out.println(controller.getPlayer1().getCharacter() + " wins!"
                    + " Press 'n' to restart");
            return true;
        } else if(win.equals(controller.getPlayer2().getName())) {
            System.out.println(controller.getPlayer2().getCharacter() + " wins!"
                    + " Press 'n' to restart");
            return true;
        } else if(win.equals("draw")) {
            System.out.println("Game is Draw! Press 'n' to restart");
            return true;
        } else if(win.equals("playing")) {
            System.out.println("Game is not finished!");
        }
        return false;
    }
    
    private void player() {
        String playername[] = new String[mode];
        for (int i = 0; i < mode; i++) {
            System.out.print("Type in Playername" + (i + 1) + ":\n"
                    + IN);
            playername[i] = scanner.next();
        }
        controller.getPlayer1().setName(playername[0]);
        if(mode == 2) {
            controller.getPlayer2().setName(playername[1]);
            System.out.println(controller.getPlayer1().getName() + IS + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + IS + controller.getPlayer2().getCharacter());
        } else {
            controller.getPlayer2().setName("Artificial Intelligence");
            System.out.println(controller.getPlayer1().getName() + IS + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + IS + controller.getPlayer2().getCharacter());
        }
    }
    
    private void modeChange() {
        while(true) {
            System.out.print("Choose your game Mode:\n"
                + "1\t-\tvs Artificial Intelligence\n"
                + "2\t-\tvs Player\n"
                + IN);
               
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

    private void setCellAI() {
        printTUI();
        List<String> l = controller.getUnSetCells();
        int random = controller.randInt(0, l.size() - 1);
        int arg0 = Integer.parseInt(String.valueOf(l.get(random).charAt(0)));
        int arg1 = Integer.parseInt(String.valueOf(l.get(random).charAt(1)));                
        controller.setValue(arg0, arg1);
        checkGameEnd();
    }
}

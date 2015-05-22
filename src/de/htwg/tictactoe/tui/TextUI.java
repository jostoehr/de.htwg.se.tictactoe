package de.htwg.tictactoe.tui;

import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.controller.impl.StateCrossPlaying;
import de.htwg.tictactoe.controller.impl.StateCrossWon;
import de.htwg.tictactoe.controller.impl.StateDraw;
import de.htwg.tictactoe.controller.impl.StateNoughtPlaying;
import de.htwg.tictactoe.controller.impl.StateNoughtWon;
import de.htwg.tictactoe.entities.Enum;
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
    private String mode;
    
    /**
     * String literal for printStatus
     */
    private String turn = " it's your turn!";
    
    /**
     * String literal is
     */
    private String is = " is ";
    
    public TextUI(TictactoeController controller) {
        this.controller = controller;
        controller.init();
        controller.setCurrentState(new StateCrossPlaying(controller));
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
            printHelp();
        } 
        if (line.equalsIgnoreCase("n")) {
            controller.init();
            System.out.println("New Game is created");
            controller.setCurrentState(new StateCrossPlaying(controller));
        }
        
        if (line.equalsIgnoreCase("m")) {
            modeChange();
            player();
            controller.init();
            controller.setCurrentState(new StateCrossPlaying(controller));
        }
        
        if (line.equalsIgnoreCase("c")) {
            if(controller.getCurrentState() instanceof StateCrossWon
                    || controller.getCurrentState() instanceof StateNoughtWon
                    || controller.getCurrentState() instanceof StateDraw 
                    || controller.isEmpty()) {
                if(controller.getPlayer1().getCharacter().equals(Enum.CROSS)) {
                    controller.getPlayer1().setCharacter(Enum.NOUGHT);
                    controller.getPlayer2().setCharacter(Enum.CROSS);
                } else {
                    controller.getPlayer1().setCharacter(Enum.CROSS);
                    controller.getPlayer2().setCharacter(Enum.NOUGHT);                
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
        
        if (line.equalsIgnoreCase("p")) {
            printStatistic();
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
        System.out.print("\n-----MENU-----\n"
                + "h\t-\tHelp\n"
                + "n\t-\tNew Game\n"
                + "m\t-\tGame Mode\n"
                + "c\t-\tChange Player1 to o, Player2 to x\n"
                + "s\t-\tSet Cell((x,y) like: 10\n"
                + "p\t-\tprint Statistics\n"
                + "q\t-\tquit\n");
    }
    
    private void printStatus() {
        if(controller.getCurrentState() instanceof StateCrossPlaying) {
            if(controller.getPlayer1().getCharacter().equals(Enum.CROSS)) {
                System.out.println(controller.getPlayer1().getName() + turn);
            } else {
                System.out.println(controller.getPlayer2().getName() + turn);
            }
        }
        if(controller.getCurrentState() instanceof StateNoughtPlaying) {
            if(controller.getPlayer2().getCharacter().equals(Enum.NOUGHT)) {
                System.out.println(controller.getPlayer2().getName() + turn);
            } else {
                System.out.println(controller.getPlayer1().getName() + turn);
            }
        }
    }
    
    private void printStatistic() {
        System.out.println(controller.getPlayer1().getName() + "(" 
                + controller.getPlayer1().getCharacter() + ")\t" 
                + controller.getPlayer2().getName() + "(" 
                + controller.getPlayer2().getCharacter() + ")");
        System.out.println("Wins: " + controller.getPlayer1().getWinCount()
                + "\t\tWins: " + controller.getPlayer2().getWinCount());
        System.out.println("Lost: " + controller.getPlayer1().getLooseCount()
                + "\t\tLost: " + controller.getPlayer2().getLooseCount());
        System.out.println("Draw: " + controller.getPlayer1().getDrawCount()
                + "\t\tDraw: " + controller.getPlayer2().getDrawCount());
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
        int j = 0;
        if(mode.equals("p")) {
            j = 2;
        } else { 
            j = 1;
        }   
        String playername[] = new String[j];
        for (int i = 0; i < j; i++) {
            System.out.println("Type in Playername" + (i + 1) + ": ");
            playername[i] = scanner.next();
        }
        controller.setPlayer1(playername[0], Enum.CROSS);
        if(j == 2) {
            controller.setPlayer2(playername[1], Enum.NOUGHT);
            System.out.println(controller.getPlayer1().getName() + is + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + is + controller.getPlayer2().getCharacter());
        } else {
            controller.setPlayer2("Artificial Intelligence", Enum.NOUGHT);
            System.out.println(controller.getPlayer1().getName() + is + controller.getPlayer1().getCharacter()
                + ", " + controller.getPlayer2().getName() + is + controller.getPlayer2().getCharacter());
        }
    }
    
    private void modeChange() {
        while(true) {
            System.out.println("Choose your game Mode:\n"
                + "a\t-\tvs Artificial Intelligence\n"
                + "p\t-\tvs Player");
            mode = scanner.next();
            if(mode.equalsIgnoreCase("a")) {
                this.mode = mode;
                break;
            }
            if(mode.equalsIgnoreCase("p")) {
                this.mode = mode;            
                break;
            }
        }
    }
}

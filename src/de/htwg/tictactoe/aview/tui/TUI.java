package de.htwg.tictactoe.aview.tui;

import com.google.inject.Inject;
import de.htwg.tictactoe.controller.IMasterController;
import de.htwg.tictactoe.util.State;
import de.htwg.tictactoe.util.Value;
import de.htwg.util.observer.IObserver;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Textual User Interface
 * @author Johannes Stöhr
 */
public class TUI implements IObserver {

    private IMasterController master;
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
    
    private final Logger logger =
            Logger.getLogger("de.htwg.tictactoe.view.tui");
    
    @Inject
    public TUI(IMasterController controller) {
        this.master = controller;
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
        logger.info(master.getGridString());
	logger.info(master.getStatus());
        printHelp();
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("h")) {
            printIndexHelp();
        } 
        if (line.equalsIgnoreCase("n")) {
            newGame();
        }
        
        if (line.equalsIgnoreCase("m")) {
            modifyMode();
        }
        
        if (line.equalsIgnoreCase("c")) {
            characterChange();
        }
        
        if (line.equalsIgnoreCase("s")) {
            setCell();
        }
        
        if (line.equalsIgnoreCase("p")) {
            printStatistic();
        }
        if (line.equalsIgnoreCase("q")) { 
            continu = false;
        }
        return continu;
    }
    
    private void characterChange(){
        if(!master.isEmpty()) {
                logger.info("Start first a new game to change Characters");
        } else {
            if(master.getPlayer1().getCharacter().equals(Value.CROSS)) {
                master.getPlayer1().setCharacter(Value.NOUGHT);
                master.getPlayer2().setCharacter(Value.CROSS);
            } else {
                master.getPlayer1().setCharacter(Value.CROSS);
                master.getPlayer2().setCharacter(Value.NOUGHT);                
            }
            logger.info("Changed Characters x and o");
            logger.info(master.getPlayer1().getName() + IS + master.getPlayer1().getCharacter()
                + ", " + master.getPlayer2().getName() + IS + master.getPlayer2().getCharacter());
            if(mode == 1 && (master.getPlayer2().getCharacter() == Value.CROSS)) {
                setCellAI();
            }
        }
    }
    
    private void modifyMode(){
        modeChange();
        player();
        master.init();
        master.setCurrentState(State.STATECROSSPLAYING);
    }
    
    private void newGame() {
        master.init();
        logger.info("New Game is created");
        master.setCurrentState(State.STATECROSSPLAYING);
        if(mode == 1 && (master.getPlayer2().getCharacter() == Value.CROSS)) {
            setCellAI();
        }
    }
    
    private void setCell() {
        if(master.win().equals("playing")) {
                String line2;
                do {
                    logger.info("Type in the Cell you want to set "
                        + "(example: 00, choose 'h' to get a Indizes Help)\n" + IN);
                    line2 = scanner.next();
                    if (line2.equalsIgnoreCase("h")) {
                        printIndexHelp();
                        logger.info("Type in the Cell you want to set\n"
                            + IN);
                        line2 = scanner.next();
                    } 
                    if (line2.matches("[0-2][0-2]")){
                        int[] arg = readToArray(line2);
                        master.setValue(arg[0], arg[1]);
                    } 
                } while(!line2.matches("[0-2][0-2]")); 
            } else {
                logger.info("Game is over, press 'n' to restart");
            }
            if(!checkGameEnd() &&
               mode == 1 &&
               master.getCurrentPlayer().getName().equals("AIntelligence")) {
               
                setCellAI(); 
            }
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
        logger.info("\n-----MENU-----\n"
                + "h\t-\tMatrix Indizes Help\n"
                + "n\t-\tNew Game\n"
                + "m\t-\tGame Mode\n"
                + "c\t-\tChange Player1 to o, Player2 to x\n"
                + "s\t-\tSet Cell((x,y) like: 10\n"
                + "p\t-\tprint Statistics\n"
                + "q\t-\tquit\n");
        logger.info(IN);
    }
    
    private void printIndexHelp() {
        logger.info("Matrix Indizes to set a cell:\n\n"
            + "00 | 01 | 02\n"
            + "------------\n"
            + "10 | 11 | 12\n"
            + "------------\n"
            + "20 | 21 | 22\n");
    }
    
    private void printStatus() {
        if(master.getCurrentState() == State.STATECROSSPLAYING ||
                master.getCurrentState() == State.STATENOUGHTPLAYING) {
            logger.info(master.getCurrentPlayer().getName() + TURN);
        }
    }
    
    private void printStatistic() {
        logger.info("\n" + master.getPlayer1().getName() + "(" 
                + master.getPlayer1().getCharacter() + ")\t" 
                + master.getPlayer2().getName() + "(" 
                + master.getPlayer2().getCharacter() + ")");
        logger.info("Wins: " + master.getPlayer1().getWinCount()
                + "\t\tWins: " + master.getPlayer2().getWinCount());
        logger.info("Lost: " + master.getPlayer1().getLostCount()
                + "\t\tLost: " + master.getPlayer2().getLostCount());
        logger.info("Draw: " + master.getPlayer1().getDrawCount()
                + "\t\tDraw: " + master.getPlayer2().getDrawCount() + "\n");
    }

    private boolean checkGameEnd() {
        String win = master.win();
        if(win.equals(master.getPlayer1().getName())) {
            logger.info(master.getPlayer1().getCharacter() + " wins!"
                    + " Press 'n' to restart");
            return true;
        } else if(win.equals(master.getPlayer2().getName())) {
            logger.info(master.getPlayer2().getCharacter() + " wins!"
                    + " Press 'n' to restart");
            return true;
        } else if(win.equals("draw")) {
            logger.info("Game is Draw! Press 'n' to restart");
            return true;
        } else if(win.equals("playing")) {
            logger.info("Game is not finished!");
        }
        return false;
    }
    
    private void player() {
        String playername[] = new String[mode];
        for (int i = 0; i < mode; i++) {
            logger.info("Type in Playername" + (i + 1) + ":\n"
                    + IN);
            playername[i] = scanner.next();
        }
        master.getPlayer1().setName(playername[0]);
        if(mode == 2) {
            master.getPlayer2().setName(playername[1]);
            logger.info(master.getPlayer1().getName() + IS + master.getPlayer1().getCharacter()
                + ", " + master.getPlayer2().getName() + IS + master.getPlayer2().getCharacter());
        } else {
            master.getPlayer2().setName("AIntelligence");
            logger.info(master.getPlayer1().getName() + IS + master.getPlayer1().getCharacter()
                + ", " + master.getPlayer2().getName() + IS + master.getPlayer2().getCharacter());
        }
    }
    
    private void modeChange() {
        while(true) {
            logger.info("Choose your game Mode:\n"
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
        List<String> l = master.getUnSetCells();
        int random = master.randInt(0, l.size() - 1);
        int arg0 = Integer.parseInt(String.valueOf(l.get(random).charAt(0)));
        int arg1 = Integer.parseInt(String.valueOf(l.get(random).charAt(1)));                
        master.setValue(arg0, arg1);
        checkGameEnd();
    }
}

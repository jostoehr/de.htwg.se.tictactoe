package de.htwg.tictactoe.tui;

import de.htwg.util.observer.IObserver;
import de.htwg.tictactoe.controller.TictactoeController;
import java.util.Scanner;

/**
 *
 * @author johannes
 */
public class TextUI implements IObserver {

    private TictactoeController controller;
    Scanner scanner;
    
    public TextUI(TictactoeController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        System.out.println(controller.getGridString());
	System.out.println(controller.getStatus());
        System.out
                .println("Please enter a command( q - quit, n - new, xyz - set cell(x,y) with x or o)");
    }
    
    public boolean processInputLine(String line){
        boolean continu = true;
        if (line.equalsIgnoreCase("q"))
        {
            continu = false;
        } 
        return continu;
    }
}

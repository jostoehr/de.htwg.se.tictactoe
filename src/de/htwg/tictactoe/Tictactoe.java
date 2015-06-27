package de.htwg.tictactoe;

import de.htwg.tictactoe.aview.gui.GUI;
import de.htwg.tictactoe.aview.gui.GUISwing;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.model.impl.Grid;
import de.htwg.tictactoe.aview.tui.TUI;
import java.util.Scanner;

public final class Tictactoe {
    private Tictactoe(){}
    private static Scanner scanner;
    private static MasterController master = new MasterController();
    
    public static void main(final String[] args) {
        
        
        //TUI tui = new TUI(master);
        //tui.printTUI();
        new GUISwing(master);

        /*boolean continu = true;
        scanner = new Scanner(System.in);
        while(continu) {
            continu = tui.processInputLine(scanner.next());
            tui.printTUI();
        }*/
    }
}

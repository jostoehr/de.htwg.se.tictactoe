package de.htwg.tictactoe;

import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.tui.TextUI;
import java.util.Scanner;

public final class Tictactoe {
    private Tictactoe(){}
    private static Scanner scanner;
    
    public static void main(final String[] args) {
        TextUI tui = new TextUI(new MasterController(new Grid()));
        tui.printTUI();

        boolean continu = true;
        scanner = new Scanner(System.in);
        while(continu) {
            continu = tui.processInputLine(scanner.next());
            tui.printTUI();
        }
    }
}

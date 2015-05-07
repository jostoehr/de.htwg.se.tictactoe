package de.htwg.tictactoe;

import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.tui.TextUI;
import java.util.Scanner;

public class Tictactoe {
    static Scanner scanner;
    String line = "";
    
    public static void main(final String[] args) {
        TextUI tui = new TextUI(new TictactoeController(new Grid()));
        tui.printTUI();
        System.out.println("test");
        boolean continu = true;
        scanner = new Scanner(System.in);
        while(continu) {
            continu = tui.processInputLine(scanner.next());
        }
    }
}

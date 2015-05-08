package de.htwg.tictactoe;

import de.htwg.tictactoe.controller.PlayerController;
import de.htwg.tictactoe.controller.TictactoeController;
import de.htwg.tictactoe.entities.Grid;
import de.htwg.tictactoe.tui.TextUI;
import java.util.Scanner;

public class Tictactoe {
    private static Scanner scanner;
    
    public static void main(final String[] args) {
        TextUI tui = new TextUI(new TictactoeController(new Grid()), new PlayerController());
        tui.printTUI();

        boolean continu = true;
        scanner = new Scanner(System.in);
        while(continu) {
            continu = tui.processInputLine(scanner.next());
        }
    }
}

package de.htwg.tictactoe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.tictactoe.aview.gui.ModePlayer;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.aview.tui.TUI;
import de.htwg.tictactoe.controller.IMasterController;
import java.util.Scanner;

public final class Tictactoe {
    private Tictactoe(){}
    private static Scanner scanner;
    
    public static void main(final String[] args) {
        
        Injector injector = Guice.createInjector(new TictactoeModule());
        IMasterController master = injector.getInstance(IMasterController.class);
        @SuppressWarnings("unused")
        ModePlayer gui = injector.getInstance(ModePlayer.class);
        TUI tui = injector.getInstance(TUI.class);
        tui.printTUI();
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processInputLine(scanner.next());		
        }
    }
    
}

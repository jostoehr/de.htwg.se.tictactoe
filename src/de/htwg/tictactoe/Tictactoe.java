package de.htwg.tictactoe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.tictactoe.aview.gui.ModePlayerSwing;
import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.aview.tui.TUI;
import de.htwg.tictactoe.controller.IMasterController;
import java.util.Scanner;

public final class Tictactoe {
    private Tictactoe(){}
    private static Scanner scanner;
    private static MasterController master = new MasterController();
    
    public static void main(final String[] args) {
        
        Injector injector = Guice.createInjector(new TictactoeModule());
        IMasterController controller = injector.getInstance(IMasterController.class);
        @SuppressWarnings("unused")
        ModePlayerSwing gui = injector.getInstance(ModePlayerSwing.class);
        TUI tui = injector.getInstance(TUI.class);
        tui.printTUI();
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processInputLine(scanner.next());		
        }
    }
    
}

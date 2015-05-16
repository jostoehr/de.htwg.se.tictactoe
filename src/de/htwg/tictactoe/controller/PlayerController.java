package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.entities.Enum;
import de.htwg.tictactoe.entities.Player;

/**
 *
 * @author johannes
 */
public class PlayerController {
    private Player player1;
    private Player player2;
    /*Zustände für das State-Pattern*/
    final static int ATTACK = 1;
    final static int WAITING = 0;
    
    /*Instanzvariable 'zustand' zum festhalten
    * in welchem Zustand wir gerade sind.
    */
    int zustand = ATTACK;
    /*
    * Mit 'anzahl' wird verfolgt wie viele 
    * Zeichen, der Spieler noch besitzt
    */
    int anzahl = 0;
    /*Ist 'anzahl' nicht null, wird der Zustand
    * auf WAITING gesetzt. 
    */
    public PlayerController(int anzahl){
        this.anzahl = anzahl;
        if (anzahl > 0){
            zustand = WAITING;
        }
    }
    /*Methode die prüft ob Spieler an der Reihe ist*/
    public void playerItsYourTurn(){
        if(zustand == ATTACK){
            System.out.println("Spieler Sie sind dran!");
        } else {
            System.out.println("Spieler Sie sind nicht dran!");
        }
    }
    /*Methode die prüft ob Spieler wartet*/
    public void playerYouAreWaiting(){
        if(zustand == WAITING) {
            System.out.println("Spieler Sie müssen warten!");
        } else {
            System.out.println("Spieler Sie müssen nicht warten!");
        }
    }
    
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    
    public final void setPlayer1(String name, Enum character) {
        this.player1 = new Player(name, character);
    }

    public final void setPlayer2(String name, Enum character) {        
        this.player2 = new Player(name, character);
    }
}

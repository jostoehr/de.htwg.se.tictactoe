/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siegfried
 */
public class PlayerTest {
    private String name;
    private String name2;
    private Enum character;
    private Enum character2;
    private Player player1;
    private Player player2;
    
    @Before
    public void setUp() {
        name = "Peter";
        name2 = "Hans";
        character2 = Enum.EMPTY;
        character = Enum.CROSS;
        player1 = new Player(name, character);
        player2 = new Player(name2, character2);
    }
    
    @Test
    public void TestGetName(){
        assertEquals("Peter", player1.getName());
        assertEquals("Hans", player2.getName());
    }
    
    @Test
    public void TestGetCharacter(){
        assertEquals(Enum.CROSS, player1.getCharacter());
        assertEquals(Enum.EMPTY, player2.getCharacter());
    }
    
    
}

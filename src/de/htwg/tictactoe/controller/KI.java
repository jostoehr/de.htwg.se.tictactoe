/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.tictactoe.controller;

/**
 *
 * @author siegfried
 */
public class KI {
    /*
    * source: http://www.java-uni.de/index.php?Seite=96
    */
    public int[][] board = new int[3][3];
       
    public int[] generateMoves(int[][] zellen){
        int anzahl = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(zellen[i][j] == 0){
                    anzahl++;
                }
            }
        }
        int[] zuege = new int[anzahl];
        anzahl = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(zellen[i][j] == 0){
                    zuege[anzahl] = i*10+j;
                    anzahl++;
                }
            }
        }
        return zuege;
    }
    
    
    public int minmax(int[][] zellen){
       
        int maximal = -5;
        int[] zuege = generateMoves(zellen);
        for (int i = 0; i < zuege.length; i++){
            zellen[zuege[i]/10][zuege[i]%10] = 1;
            int wert = minmaxO(zellen);
            if (wert > maximal){
                maximal = wert;
            }
            zellen[zuege[i]/10][zuege[i]%10] = 0;
        }
        return maximal;
    }
    
    public int minmaxO(int[][] zellen){   
      int min = +5;
      int[] zuege = generateMoves(zellen);
      
      for (int i=0; i < zuege.length; i++) {
         zellen[zuege[i]/10][zuege[i]%10] = -1;
         
         int wert = minmaxO(zellen);
         if (wert < min)
            min = wert;
         zellen[zuege[i]/10][zuege[i]%10] = 0;
      }
      
      return min;
   }
}

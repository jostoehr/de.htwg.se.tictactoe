/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.util.observer;

import java.util.Vector;

/**
 *
 * @author siegfried
 */
public class Observable {
    protected Vector<IObserver> subscribers = new Vector<IObserver>(2);
    
    public void addObserver(IObserver s){
        subscribers.addElement(s);
    }
    
    public void removeObserver(IObserver s){
        subscribers.removeElement(s);
    }
    
    public void removeAllObservers(){
        subscribers.removeAllElements();
    }
    
    public void notifyObservers(){
        for(IObserver observe: subscribers){
            observe.update();
        }
    }
}

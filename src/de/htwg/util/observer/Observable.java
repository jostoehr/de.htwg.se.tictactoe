/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.util.observer;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siegfried
 */
public class Observable {
    private List<IObserver> subscribers = new LinkedList<IObserver>();
    
    public void addObserver(IObserver s){
        subscribers.add(s);
    }
    
    public void removeObserver(IObserver s){
        subscribers.remove(s);
    }
    
    public void removeAllObservers(){
        for(int i = 0; i < subscribers.size(); i++)
        {
            subscribers.remove(i);
        }
    }
    
    public void notifyObservers(){
        for(IObserver observe: subscribers){
            observe.update();
        }
    }
}

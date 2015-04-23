/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.util.observer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author siegfried
 */
public class ObservableTest {
    
    private boolean ping=false;
	private TestObserver testObserver;
	private Observable testObservable;
	
	class TestObserver implements IObserver {
		@Override
		public void update() {
			ping=true;
		}
		
	}

	@Before
	public void setUp() throws Exception {
		testObserver = new TestObserver();
		testObservable = new Observable();
		testObservable.addObserver(testObserver);
	}

	@Test
	public void testNotify() {
		assertFalse(ping);
		testObservable.notifyObservers();
		assertTrue(ping);
	}
	
	@Test
	public void testRemove() {
		assertFalse(ping);
		testObservable.removeObserver(testObserver);
		testObservable.notifyObservers();
		assertFalse(ping);
	}
	
	@Test
	public void testRemoveAll() {
		assertFalse(ping);
		testObservable.removeAllObservers();
		testObservable.notifyObservers();
		assertFalse(ping);
	}
}

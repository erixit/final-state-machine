package com.erixit.fsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class FiniteStateMachineTest {

	@Test
	public void testValid() {
		State s;
		Input in = new Input("aabbbc.");
		for(s = States.Init; s != null && s != States.Fail; s = s.next(in)) {}
		  
		assertNull(s);
	}

	@Test
	public void testNoA() {
		State s;
		Input in = new Input("b.");
		for(s = States.Init; s != null && s != States.Fail; s = s.next(in)) {}
		  
		assertEquals(States.Fail, s);
	}
	
	@Test(expected = StringIndexOutOfBoundsException.class)
	public void testUnfinished() {
		State s;
		Input in = new Input("aabbbc");
		for(s = States.Init; s != null && s != States.Fail; s = s.next(in)) {}
		  
		assertNull(s);
	}
}

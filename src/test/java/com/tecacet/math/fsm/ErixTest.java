package com.tecacet.math.fsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This is the test from
 * http://www.javacodegeeks.com/2012/03/automaton-implementation-in-java.html,
 * adapted for the tecacet library. Remarks: - because the valid actions are
 * enumerated, there is no need to put the machine in a failed state when an
 * unexpected input is encountered, it's just impossible here to provide an
 * unexpected action.
 * 
 * 
 * @author evv
 *
 */

enum ABCState {
	INIT, A, B, C, FINAL
}

enum ABCAction {
	a, b, c, end
}

public class ErixTest {

	private Alphabet<ABCAction> alphabet;
	private DeterministicFiniteAutomaton<ABCState, ABCAction> machine;

	@Before
	public void setUp() throws Exception {
		alphabet = new Alphabet<>(ABCAction.values());
		DFABuilder<ABCState, ABCAction> builder = DFA.newDFA(alphabet);
		// @formatter:off
		builder.setInitialState(ABCState.INIT)
		        .addFinalState(ABCState.FINAL)
				.addTransition(ABCState.INIT, ABCState.A, ABCAction.a)
				.addTransition(ABCState.A, ABCState.A, ABCAction.a)
				.addTransition(ABCState.A, ABCState.B, ABCAction.b)
				.addTransition(ABCState.A, ABCState.C, ABCAction.c)
				.addTransition(ABCState.A, ABCState.FINAL, ABCAction.end)
				.addTransition(ABCState.B, ABCState.B, ABCAction.b)
				.addTransition(ABCState.B, ABCState.C, ABCAction.c)				
				.addTransition(ABCState.B, ABCState.FINAL, ABCAction.end)
				.addTransition(ABCState.C, ABCState.C, ABCAction.c)				
				.addTransition(ABCState.C, ABCState.FINAL, ABCAction.end)
		;		
		// @formatter:on
		machine = builder.build();
	}

	@Test
	public void testValid() throws Exception {
		List<ABCAction> allowable = new ArrayList<>();
		allowable.add(ABCAction.a);
		allowable.add(ABCAction.a);		
		allowable.add(ABCAction.b);
		allowable.add(ABCAction.b);	
		allowable.add(ABCAction.b);
		allowable.add(ABCAction.c);			
		allowable.add(ABCAction.end);
		BasicWord<ABCAction> word = new BasicWord<>(allowable);
		assertTrue(machine.accepts(word));

		List<ABCState> path = machine.getPath(word);
		assertEquals("[INIT, A, A, B, B, B, C, FINAL]", path.toString());
	}

	@Test
	public void testNoA() throws Exception {
		List<ABCAction> allowable = new ArrayList<>();
		allowable.add(ABCAction.b);	
		allowable.add(ABCAction.end);
		BasicWord<ABCAction> word = new BasicWord<>(allowable);		
		try {
			machine.accepts(word);
			fail();
		} catch (InvalidTransitionException ite) {
			assertEquals("There is no transition from state INIT on symbol b.", ite.getMessage());
		}
	}
	
	@Test
	public void testUnfinished() throws Exception {
		List<ABCAction> allowable = new ArrayList<>();
		allowable.add(ABCAction.a);
		allowable.add(ABCAction.a);		
		allowable.add(ABCAction.b);
		allowable.add(ABCAction.b);	
		allowable.add(ABCAction.b);
		allowable.add(ABCAction.c);			
		BasicWord<ABCAction> word = new BasicWord<>(allowable);
		// refused, as C is not a final state
		assertFalse(machine.accepts(word));
	}	
}

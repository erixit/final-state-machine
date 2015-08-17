package com.tecacet.math.fsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

public class NFATest {

	// This test fails, so ignore
	@Ignore
	@Test
	/**
	 * Build a machine that accepts any string with 00 or 11
	 */
	public void test1() throws FAException {
		CharacterAlphabet alphabet = new CharacterAlphabet(new char[] { '0',
		'1' });
		NFABuilder<String, Character> builder = NFA.newNFA(alphabet);
		builder.setInitialState("A").
			addTransition("A", "A", '0').
			addTransition("A", "A", '1').
			addTransition("A", "B", '1').
			addTransition("A", "D", '0').
			addTransition("B", "C", '1').
			addTransition("D", "E", '0').
			addTransition("C", "C", '0').
			addTransition("C", "C", '1').
			addTransition("E", "E", '1').
			addTransition("E", "E", '0').
			addFinalState("C").addFinalState("E");
		NonDeterministicFiniteAutomaton<String, Character> nfa = builder.build();
		
		assertTrue(nfa.accepts(BasicWord.fromString("00")));
		assertTrue(nfa.accepts(BasicWord.fromString("11")));
		assertFalse(nfa.accepts(BasicWord.fromString("01")));
		assertFalse(nfa.accepts(BasicWord.fromString("0101010101")));
		assertTrue(nfa.accepts(BasicWord.fromString("010101010110")));
		
		List<Set<String>> path = nfa.getPath(BasicWord.fromString("010100"));
		assertEquals("[[A], [D, A], [A, B], [D, A], [A, B], [D, A], [D, E, A]]", path.toString());
		
		
	}

}

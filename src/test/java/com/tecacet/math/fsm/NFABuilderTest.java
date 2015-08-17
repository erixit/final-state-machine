package com.tecacet.math.fsm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NFABuilderTest {

	@Test
	public void testBuild() throws FAException {
		CharacterAlphabet alphabet = new CharacterAlphabet(new char[] { '0',
				'1' });

		NFABuilder<String, Character> builder = NFA.newNFA(alphabet);
		builder.setInitialState("A").addFinalState("C")
				.addTransition("A", "A", '0').addTransition("A", "B", '1')
				.addTransition("B", "C", '0').addTransition("C", "B", '1')
				.addTransition("B", "A", '0');
		NonDeterministicFiniteAutomaton<String, Character> machine = builder
				.build();
		assertFalse(machine.accepts(BasicWord.fromString("10011010")));
		assertFalse(machine.accepts(BasicWord.fromString("01010011")));
		assertTrue(machine.accepts(BasicWord.fromString("0010010")));
		assertTrue(machine.accepts(BasicWord.fromString("101010")));
	}
}

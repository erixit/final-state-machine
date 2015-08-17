package com.tecacet.math.fsm;

import static org.junit.Assert.*;

import org.junit.Test;

public class DFABuilderTest {

	@Test
	public void testBuild() throws FAException {
		CharacterAlphabet alphabet = new CharacterAlphabet(new char[] { '0',
				'1' });

		DFABuilder<String, Character> builder = DFA.newDFA(alphabet);
		builder.setInitialState("A").addFinalState("D")
				.addTransition("A", "B", '0').addTransition("A", "C", '1')
				.addTransition("B", "D", '1').addTransition("D", "B", '1')
				.addTransition("C", "D", '0');
		DeterministicFiniteAutomaton<String, Character> machine = builder
				.build();
		assertTrue(machine.accepts(BasicWord.fromString("101111")));
	}

}

package com.tecacet.math.fsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class DFATest {

	@Test
	public void test1() throws Exception {

		CharacterAlphabet alphabet = new CharacterAlphabet(new char[] { '0',
				'1' });

		DFABuilder<String, Character> builder = DFA.newDFA(alphabet);
		builder.setInitialState("A").addFinalState("B")
				.addTransition("A", "A", '0').addTransition("A", "B", '1')
				.addTransition("B", "A", '1').addTransition("B", "B", '0');
		DeterministicFiniteAutomaton<String, Character> machine = builder
				.build();
		String s = ((DFA<String, Character>) machine).showTransitionDiagram();
		System.out.println(s);

		assertTrue(machine.accepts(BasicWord.fromString("1011")));
		assertTrue(machine.accepts(BasicWord.fromString("0010000011000101")));
		assertFalse(machine.accepts(BasicWord.fromString("1111")));
		assertFalse(machine.accepts(BasicWord.fromString("1010101")));

		List<String> path = machine.getPath(BasicWord.fromString("1011"));
		assertEquals("[A, B, B, A, B]", path.toString());
	}

	@Test
	public void test2() throws FABuilderException, FAException {
		DFABuilder<String, Character> builder = DFA
				.newDFA(new CharacterAlphabet(new char[] { 'a', 'b', 'c' }));
		builder.setInitialState("S0");

		builder.addTransition("S0", "S0", 'b');
		builder.addTransition("S0", "S1", 'a');
		builder.addTransition("S0", "S2", 'c');
		builder.addTransition("S1", "S0", 'b');
		builder.addTransition("S1", "S0", 'c');
		builder.addTransition("S1", "S2", 'a');
		builder.addTransition("S2", "S0", 'a');
		builder.addTransition("S2", "S0", 'b');
		builder.addTransition("S2", "S1", 'c');
		builder.addFinalState("S2");

		// TODO test
		DeterministicFiniteAutomaton<String, Character> machine = builder
				.build();

		String s = ((DFA<String, Character>) machine).showTransitionDiagram();
		System.out.println(s);
		try {
			machine.getNextState("X", 'c');
			fail();
		} catch (InvalidStateException ise) {
			assertEquals("State X does not exist.", ise.getMessage());
		}
	}

	@Test
	public void testBuilderException() throws FABuilderException {
		DFABuilder<String, Character> builder = DFA
				.newDFA(new CharacterAlphabet(new char[] { 'a', 'b', 'c' }));

		try {
			builder.build();
			fail();
		} catch (FABuilderException e) {
			assertEquals("Initial state is not specficied.", e.getMessage());
		}

		builder.setInitialState("S0");

		try {
			builder.setInitialState("S0");
			fail();
		} catch (FABuilderException e) {
			assertEquals("Initial state already set.", e.getMessage());
		}

		try {
			builder.addTransition("S0", "S1", 'd');
			fail();
		} catch (FABuilderException e) {
			assertEquals("d is not a valid alphabet symbol.", e.getMessage());
		}

	}

}

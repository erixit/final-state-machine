package com.tecacet.math.fsm;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleMooreMachineTest {

	@Test
	public void test1() throws Exception {

		CharacterAlphabet alphabet = new CharacterAlphabet(new char[] { '0',
				'1' });

		MooreMachineBuilder<String, Character, Character> builder = SimpleMooreMachine
				.newMachine(alphabet, alphabet);
		builder.setInitialState("A").addFinalState("B")
				.addTransition("A", "A", '0').addTransition("A", "B", '1')
				.addTransition("B", "A", '1').addTransition("B", "B", '0');
		builder.addOutput("B", '1');
		builder.addFinalState("F");
		MooreMachine<String, Character, Character> machine = builder.build();

		assertTrue(machine.accepts(BasicWord.fromString("1011")));
		
		assertTrue(machine.accepts(BasicWord.fromString("0010000011000101")));
		assertFalse(machine.accepts(BasicWord.fromString("1111")));
		assertFalse(machine.accepts(BasicWord.fromString("1010101")));
		
		assertEquals("A", machine.getInitialState());
		
		String nextState = 
				machine.getNextState("A", BasicWord.fromString("001"));
		assertEquals("B", nextState);
		assertEquals('1', machine.getOutput("B").charValue());
		

		assertEquals(machine.getAlphabet(), machine.getOutputAlphabet());
		
		
	}

}

package com.tecacet.math.fsm;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class AlphabetTest {

	@Test
	public void testAlphabet() {
		Alphabet<Character> alphabet = new Alphabet<Character>(new Character[]{'a','b'});
		assertEquals(2, alphabet.getSymbolSet().size());
		assertFalse(alphabet.contains('g'));
		assertTrue(alphabet.contains('b'));
		
		Alphabet<Character> otherAlphabet = new Alphabet<Character>(new HashSet<Character>());
		assertTrue(otherAlphabet.getSymbolSet().isEmpty());
	}

}

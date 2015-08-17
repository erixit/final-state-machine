package com.tecacet.math.fsm;

/**
 * An <i>Alphabet</i> is a finite set of Symbols. In this implementation a
 * symbol is represented by a character and a string (or word) by a
 * java.lang.String
 */
public class CharacterAlphabet extends Alphabet<Character> {

	/**
	 * Create an alphabet with all lowercase characters
	 */
	public CharacterAlphabet() {
		super();
		for (char c = 'a'; c <= 'z'; c++) {
			symbols.add(c);
		}
	}

	public CharacterAlphabet(char[] symbols) {
		super();
		for (char c : symbols) {
			this.symbols.add(c);
		}
	}

	public void addSymbol(char c) {
		symbols.add(c);
	}

	public boolean contains(char c) {
		return this.contains(Character.valueOf(c));
	}

	public char[] getSymbols() {
		char[] array = new char[symbols.size()];
		int i = 0;
		for (Character c : symbols) {
			array[i++] = c;
		}
		return array;
	}

	public String toString() {
		return new String(getSymbols());
	}
}

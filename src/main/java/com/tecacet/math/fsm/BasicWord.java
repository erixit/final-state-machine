package com.tecacet.math.fsm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Basic alphabet-independent implementation of Word. This implementation does
 * not validate if symbols added belong to a particular alphabet.
 * 
 * @param <C>
 */
public class BasicWord<C> implements Word<C> {

	private List<C> symbols = new ArrayList<C>();

	public BasicWord(Collection<C> string) {
		symbols.addAll(string);
	}

	public BasicWord(C[] string) {
		for (C symbol : string) {
			symbols.add(symbol);
		}
	}

	@Override
	public C symbolAt(int index) {
		return symbols.get(index);
	}

	@Override
	public int length() {
		return symbols.size();
	}

	@Override
	public Word<C> substring(int start, int end) {
		List<C> subList = symbols.subList(start, end);
		return new BasicWord<C>(subList);
	}

	@Override
	public Word<C> substring(int start) {
		return substring(start, symbols.size());
	}

	@Override
	public List<C> asList() {
		return symbols;
	}

	public static Word<Character> fromString(String s) {
		List<Character> ac = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			ac.add(s.charAt(i));
		}
		return new BasicWord<Character>(ac);
	}

	@Override
	public String toString() {
		return String.valueOf(symbols.toArray(new Character[symbols.size()]));
	}
}

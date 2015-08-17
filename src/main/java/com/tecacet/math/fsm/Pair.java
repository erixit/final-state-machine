package com.tecacet.math.fsm;

/* A State-symbol pair identifies a unique transition */
class Pair<S,C> {
	S state;
	C symbol;

	Pair(S state, C c) {
		this.state = state;
		this.symbol = c;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		Pair<S,C> pair = (Pair<S,C>) object;
		return (state.equals(pair.state) && equals(symbol,pair.symbol));
	}

	public int hashCode() {
		// TODO better hash code
		return (state.hashCode() + (31 ^ state.toString().length())
				* hashCode(symbol));
	}

	private boolean equals(C symbol1, C symbol2) {
		if (symbol1 == null) {
			return symbol2 == null;
		}
		if (symbol2 == null) {
			return false;
		}
		return symbol1.equals(symbol2);
	}
	
	private int hashCode(C symbol) {
		return symbol == null ? 0 : symbol.hashCode();
	}
} 
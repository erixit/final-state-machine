package com.tecacet.math.fsm;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractFiniteAutomaton<S, C> implements
		FiniteAutomaton<C> {

	protected final Alphabet<C> alphabet;
	protected S initialState = null;
	protected Set<S> states = new LinkedHashSet<S>();
	protected Set<S> finalStates = new HashSet<S>();

	protected AbstractFiniteAutomaton(Alphabet<C> alphabet) {
		this.alphabet = alphabet;
	}

	@Override
	public Alphabet<C> getAlphabet() {
		return alphabet;
	}

	protected void addFinalState(S state) {
		states.add(state);
		finalStates.add(state);
	}

	protected boolean containsState(S state) {
		return (states != null && states.contains(state));
	}

	public S getInitialState() {
		return initialState;
	}
	
	public boolean isFinal(S state) {
		return finalStates.contains(state);
	}

}

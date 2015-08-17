package com.tecacet.math.fsm;

import java.util.List;
import java.util.Set;

public interface NonDeterministicFiniteAutomaton<S, C> extends
		FiniteAutomaton<C> {

	/**
	 * transition function delta
	 * 
	 * @param q
	 * @param c
	 * @return
	 * @throws InvalidTransitionException
	 */
	Set<S> getNextStates(S q, C c) throws InvalidTransitionException;

	List<Set<S>> getPath(Word<C> word);

}

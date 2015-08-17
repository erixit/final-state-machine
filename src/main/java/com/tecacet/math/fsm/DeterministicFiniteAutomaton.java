package com.tecacet.math.fsm;

import java.util.List;

/**
 * A Deterministic Finite Automaton consists of: 1. An Alphabet A 2. A set of States S 3. A function delta: SxA -> S 4.
 * An initial state s0 in S
 * 
 * @param S
 *            parametrizes the set of states
 * @param C
 *            parametrizes the set of alphabet symbols
 */
public interface DeterministicFiniteAutomaton<S, C> extends FiniteAutomaton<C>, StateTransitionDiagram<S, C> {

    S getInitialState();

    S getNextState(S from, Word<C> word) throws FAException;

    /**
     * Get the path of states exercised by a string of symbols
     * @param word
     * @return
     * @throws InvalidTransitionException if any symbol forces an invalid transition
     */
	List<S> getPath(Word<C> word) throws InvalidTransitionException;

}

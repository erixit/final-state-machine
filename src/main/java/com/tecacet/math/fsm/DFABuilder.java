package com.tecacet.math.fsm;

/**
 * 
 * Builder for a Deterministic Finite Automaton. For the automaton to be
 * complete it must have an initial state and at east one final state.
 * 
 * @param <S>
 * @param <C>
 */
public interface DFABuilder<S, C> extends FABuilder<S, C> {

	DeterministicFiniteAutomaton<S, C> build() throws FABuilderException;

}
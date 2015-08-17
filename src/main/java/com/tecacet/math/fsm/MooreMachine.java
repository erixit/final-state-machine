package com.tecacet.math.fsm;

/**
 * a Moore machine is a finite-state machine whose output values are determined
 * solely by its current state.
 * 
 * @author dimitri
 * 
 * @param <S>
 *            State type
 * @param <CI>
 *            Input Alphabet type
 * @param <CO>
 *            Output Alphabet type
 */
public interface MooreMachine<S, CI, CO> extends
		DeterministicFiniteAutomaton<S, CI> {

	Alphabet<CO> getOutputAlphabet();

	CO getOutput(S s);

}

package com.tecacet.math.fsm;

/**
 * a Mealy machine is a finite-state machine whose output values are determined
 * both by its current state and the current inputs.
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
public interface MealyMachine<S, CI, CO> extends
		DeterministicFiniteAutomaton<S, CI> {

	Alphabet<CO> getOutputAlphabet();

	CO getOutput(S q, CI c);

}

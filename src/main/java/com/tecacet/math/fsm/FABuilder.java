package com.tecacet.math.fsm;

public interface FABuilder<S,C> {

	FABuilder<S, C> setInitialState(S initialState) throws FABuilderException;

	FABuilder<S, C> addFinalState(S state) throws FABuilderException;

	FABuilder<S, C> addTransition(S from, S to, C c)
			throws FABuilderException;

}

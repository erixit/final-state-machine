package com.tecacet.math.fsm;

public interface NFABuilder<S, C> extends FABuilder<S, C> {

    @Override
    NFABuilder<S, C> setInitialState(S initialState) throws FABuilderException;

    @Override
    NFABuilder<S, C> addFinalState(S state) throws FABuilderException;

    NFABuilder<S, C> addTransition(S from, S to, C c) throws FABuilderException;

    NonDeterministicFiniteAutomaton<S, C> build() throws FABuilderException;

}

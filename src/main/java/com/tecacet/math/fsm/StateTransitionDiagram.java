package com.tecacet.math.fsm;

/**
 * 
 * A state transition diagram describes how states are connected via symbol inputs.
 * The transition function delta: SxC -> S is sufficient to describe the diagram.
 *
 * @param <S> parameterizes the set of states
 * @param <C> parameterizes the set of input symbols
 */
public interface StateTransitionDiagram<S,C> {

    S getNextState(S from, C symbol) throws FAException;

}

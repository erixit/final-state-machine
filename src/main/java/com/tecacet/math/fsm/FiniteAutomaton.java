package com.tecacet.math.fsm;

public interface FiniteAutomaton<C> {
     
    Alphabet<C> getAlphabet();
    
    /** 
     * A word is <i>accepted</i> by the FA if it causes the FA 
     * to transition from the initial state q0 to a final state 
    */
    boolean accepts(Word<C> word) throws FAException;
}

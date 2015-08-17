package com.tecacet.math.fsm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NFA<S, C> extends AbstractFiniteAutomaton<S, C> implements NonDeterministicFiniteAutomaton<S, C> {

    private Map<Pair<S, C>, Set<S>> transitions = new HashMap<Pair<S, C>, Set<S>>();

    private class PrivateNFABuilder implements NFABuilder<S, C> {

        @Override
        public NFABuilder<S, C> setInitialState(S initialState) throws FABuilderException {
            if (null != NFA.this.initialState) {
                throw new FABuilderException("Initial state already set.");
            }
            NFA.this.initialState = initialState;
            states.add(initialState);
            return this;
        }

        @Override
        public NFABuilder<S, C> addFinalState(S state) throws FABuilderException {
            NFA.this.addFinalState(state);
            return this;
        }

        @Override
        public NFABuilder<S, C> addTransition(S from, S to, C c) throws FABuilderException {
            NFA.this.addTransition(from, to, c);
            return this;
        }

        @Override
        public NonDeterministicFiniteAutomaton<S, C> build() throws FABuilderException {
            if (NFA.this.initialState == null) {
                throw new FABuilderException("Initial state is not specficied.");
            }
            if (NFA.this.finalStates.isEmpty()) {
                throw new FABuilderException("There must be at least one final state");
            }
            return NFA.this;
        }

    }

    private NFA(Alphabet<C> alphabet) {
        super(alphabet);
    }

    public void addTransition(S from, S to, C c) {
        Set<S> states = transitions.get(new Pair<S, C>(from, c));
        if (states == null) {
            states = new HashSet<S>();
            transitions.put(new Pair<S, C>(from, c), states);
        }
        states.add(to);
    }

    @Override
    public boolean accepts(Word<C> word) throws FAException {
        return someStateIsFinal(delta_bar(initialState, word));
    }

    @Override
    public List<Set<S>> getPath(Word<C> word) {
        List<Set<S>> path = new LinkedList<Set<S>>();
        Set<S> states = new HashSet<S>();
        states.add(initialState);
        path.add(states);
        for (C symbol : word.asList()) {
            states = getNextStates(states, symbol);
            path.add(states);
        }
        return path;
    }

    private Set<S> getNextStates(Set<S> states, C symbol) {
        Set<S> nextStates = new HashSet<S>();
        for (S state : states) {
            nextStates.addAll(getNextStates(state, symbol));
        }
        return nextStates;
    }

    private boolean someStateIsFinal(Set<S> states) {
        for (S state : states) {
            if (super.isFinal(state)) {
                return true;
            }
        }
        return false;
    }

    /* Internal transition function */
    private Set<S> delta(S from, C symbol) {
        return getNextStates(from, symbol);
    }

    private Set<S> delta_bar(S from, Word<C> word) {
        Set<S> states = new HashSet<S>();
        if (word.length() == 0) {
            states.add(from);
            return states;
        }
        if (word.length() == 1) {
            return delta(from, word.symbolAt(0));
        } else {
            Set<S> nextStates = delta(from, word.symbolAt(0));
            for (S state : nextStates) {
                states.addAll(delta_bar(state, word.substring(1)));
            }
            return states;
        }
    }

    @Override
    public Set<S> getNextStates(S state, C symbol) {
        Set<S> states = transitions.get(new Pair<S, C>(state, symbol));
        if (states == null) {
            states = new HashSet<S>();
        }
        //add epsilon transitions
        Set<S> epsilonStates = transitions.get(new Pair<S, C>(state, null));
        if (epsilonStates != null) {
            states.addAll(epsilonStates);
        }
        return states;
    }

    private NFABuilder<S, C> buidler = new PrivateNFABuilder();

    public static <S, C> NFABuilder<S, C> newNFA(Alphabet<C> alphabet) {
        return new NFA<S, C>(alphabet).buidler;
    }

}

package com.tecacet.math.fsm;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DFA<S, C> extends AbstractFiniteAutomaton<S, C> implements
		DeterministicFiniteAutomaton<S, C> {

	private StateTransitionTable<S, C> transitionTable = new StateTransitionTable<S, C>();

	private class PrivateDFABuilder implements DFABuilder<S, C> {

		@Override
		public DFABuilder<S, C> setInitialState(S initialState)
				throws FABuilderException {
			if (null != DFA.this.initialState) {
				throw new FABuilderException("Initial state already set.");
			}
			DFA.this.initialState = initialState;
			states.add(initialState);
			return this;
		}

		@Override
		public DFABuilder<S, C> addFinalState(S state)
				throws FABuilderException {
			DFA.this.addFinalState(state);
			return this;
		}

		@Override
		public DFABuilder<S, C> addTransition(S from, S to, C c)
				throws FABuilderException {
			DFA.this.addTransition(from, to, c);
			return this;
		}

		@Override
		public DeterministicFiniteAutomaton<S, C> build()
				throws FABuilderException {
			if (DFA.this.getInitialState() == null) {
				throw new FABuilderException("Initial state is not specficied.");
			}
			if (DFA.this.finalStates.isEmpty()) {
				throw new FABuilderException(
						"There must be at least one final state");
			}
			return DFA.this;
		}
	}

	private DFA(Alphabet<C> alphabet) {
		super(alphabet);
	}

	private void addTransition(S from, S to, C c) throws FABuilderException {
		if (!containsState(from)) {
			throw new FABuilderException(String.format(
					"State %s does not exist", from));
		}
		if (!alphabet.contains(c)) {
			throw new FABuilderException(String.format(
					"%s is not a valid alphabet symbol.", c));
		}
		states.add(to);
		transitionTable.addTransition(from, c, to);
	}

	@Override
	public S getNextState(S from, C c) throws FAException {
		if (!containsState(from)) {
			throw new InvalidStateException(String.format(
					"State %s does not exist.", from));
		}
		return delta(from, c);
	}

	@Override
	public S getNextState(S from, Word<C> word)
			throws InvalidTransitionException {
		return delta_bar(from, word);
	}

	@Override
	public boolean accepts(Word<C> word) throws InvalidTransitionException {
		return isFinal(delta_bar(initialState, word));
	}

	@Override
	public List<S> getPath(Word<C> word) throws InvalidTransitionException {
		List<S> path = new LinkedList<S>();
		S state = getInitialState();
		path.add(state);
		for (C symbol : word.asList()) {
			state = delta(state, symbol);
			path.add(state);
		}
		return path;
	}

	/* Internal transition function */
	protected S delta(S from, C symbol) throws InvalidTransitionException {
		return transitionTable.getNextState(from, symbol);
	}

	private S delta_bar(S from, Word<C> word) throws InvalidTransitionException {
		if (word.length() == 0) {
			return from;
		}
		if (word.length() == 1) {
			return delta(from, word.symbolAt(0));
		} else {
			return delta_bar(delta(from, word.symbolAt(0)), word.substring(1));
		}
	}

	
	private DFABuilder<S, C> buidler = new PrivateDFABuilder();

	public static <S, C> DFABuilder<S, C> newDFA(Alphabet<C> alphabet) {
		return new DFA<S, C>(alphabet).buidler;
	}

	public String showTransitionDiagram() throws FAException {
		StringBuilder sb = new StringBuilder();
		sb.append(" | ");
		for (S state : states) {
			sb.append(state + " | ");
		}
		sb.append("\n");

		// create rows
		Collection<C> symbols = alphabet.getSymbolSet();
		for (C symbol : symbols) {
			sb.append(symbol + "| ");
			for (S state : states) {
				S nextState = getNextState(state, symbol);
				sb.append(nextState + " | ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}

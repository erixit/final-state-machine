package com.tecacet.math.fsm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Simple implementation of the Moore Machine as a DFA with an output mapping
 * for each state.
 * 
 */
public class SimpleMooreMachine<S, CI, CO> implements MooreMachine<S, CI, CO> {

	private final Alphabet<CO> outputAlphabet;
	private Map<S, CO> outputMap = new HashMap<S, CO>();
	private DeterministicFiniteAutomaton<S, CI> dfa;
	private final DFABuilder<S, CI> delegate;

	private class SimpleMooreMachineBuilder implements
			MooreMachineBuilder<S, CI, CO> {

		@Override
		public DFABuilder<S, CI> setInitialState(S initialState)
				throws FABuilderException {
			return (DFABuilder<S, CI>) delegate.setInitialState(initialState);
		}

		@Override
		public DFABuilder<S, CI> addFinalState(S state)
				throws FABuilderException {
			return (DFABuilder<S, CI>) delegate.addFinalState(state);
		}

		@Override
		public DFABuilder<S, CI> addTransition(S from, S to, CI c)
				throws FABuilderException {
			return (DFABuilder<S, CI>) delegate.addTransition(from, to, c);
		}

		@Override
		public MooreMachine<S, CI, CO> build() throws FABuilderException {
			dfa = delegate.build();
			return SimpleMooreMachine.this;
		}

		@Override
		public void addOutput(S state, CO output) {
			outputMap.put(state, output);
		}

	}

	private SimpleMooreMachine(DFABuilder<S, CI> dfaBuilder,
			Alphabet<CO> outputAlphabet) {
		this.delegate = dfaBuilder;
		this.outputAlphabet = outputAlphabet;
	}

	@Override
	public Alphabet<CO> getOutputAlphabet() {
		return outputAlphabet;
	}

	@Override
	public CO getOutput(S s) {
		return outputMap.get(s);
	}

	public Alphabet<CI> getAlphabet() {
		return dfa.getAlphabet();
	}

	public boolean accepts(Word<CI> word) throws FAException {
		return dfa.accepts(word);
	}

	public S getInitialState() {
		return dfa.getInitialState();
	}

	public S getNextState(S from, CI symbol) throws FAException {
		return dfa.getNextState(from, symbol);
	}

	public S getNextState(S from, Word<CI> word) throws FAException {
		return dfa.getNextState(from, word);
	}
	
	@Override
	public List<S> getPath(Word<CI> word) throws InvalidTransitionException {
		return dfa.getPath(word);
	}

	private MooreMachineBuilder<S, CI, CO> builder = new SimpleMooreMachineBuilder();

	public static <S, CI, CO> MooreMachineBuilder<S, CI, CO> newMachine(
			Alphabet<CI> inputAlphabet, Alphabet<CO> outputAlphabet) {
		DFABuilder<S, CI> dfaBuilder = DFA.newDFA(inputAlphabet);
		SimpleMooreMachine<S, CI, CO> machine = new SimpleMooreMachine<S, CI, CO>(
				dfaBuilder, outputAlphabet);
		return machine.builder;

	}

	
}

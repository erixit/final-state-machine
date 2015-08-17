package com.tecacet.math.fsm;

import java.util.HashMap;
import java.util.Map;

public class StateTransitionTable<S, C> implements StateTransitionDiagram<S, C> {

	private final Map<Pair<S, C>, S> transitionMap = new HashMap<Pair<S, C>, S>();

	public void addTransition(S fromState, C symbol, S toState) {
		transitionMap.put(new Pair<S, C>(fromState, symbol), toState);
	}

	public S getNextState(S from, C symbol) throws InvalidTransitionException {
		if (!transitionMap.containsKey(new Pair<S, C>(from, symbol))) {
			throw new InvalidTransitionException(String.format(
					"There is no transition from state %s on symbol %s.", from,
					symbol));
		}
		return transitionMap.get(new Pair<S, C>(from, symbol));
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder();
		for (Pair<S,C> pair : transitionMap.keySet()) {
			sb.append("State: " + pair.state + ", symbol = " + pair.symbol
					+ "\n");
			S state = transitionMap.get(new Pair<S,C>(pair.state, pair.symbol));
			sb.append(" gives " + state + "\n");
			sb.append("map contains this key = "
					+ transitionMap.containsKey(new Pair<S,C>(pair.state,
							pair.symbol)));
			sb.append("map contains this value = "
					+ transitionMap.containsValue(pair.state));
		}
		return sb.toString();
	}

}

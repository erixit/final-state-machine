package com.tecacet.math.fsm;

@SuppressWarnings("serial")
public class InvalidTransitionException extends FAException {

    public InvalidTransitionException(String message) {
        super(message);
    }

}

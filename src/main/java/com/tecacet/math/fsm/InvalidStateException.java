package com.tecacet.math.fsm;

/**
 * This exception is thrown if the user references a state that has not been defined in the state machine
 *
 */
@SuppressWarnings("serial")
public class InvalidStateException extends FAException {

    public InvalidStateException(String message) {
        super(message);
    }

}

package com.erixit.fsm;

public interface State {
    public State next(Input word);
}

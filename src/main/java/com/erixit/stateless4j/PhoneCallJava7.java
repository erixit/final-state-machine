package com.erixit.stateless4j;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Action;

public class PhoneCallJava7 {

    private StateMachine<State, Trigger> phoneCall;
    
	@Before
	public void setUp() {
        Action callStartTimer = new Action() {
            @Override
            public void doIt() {
                startCallTimer();
            }
        };
        Action callStopTimer = new Action() {
            @Override
            public void doIt() {
                stopCallTimer();
            }
        };

        StateMachineConfig<State, Trigger> phoneCallConfig = new StateMachineConfig<>();

        phoneCallConfig.configure(State.OffHook)
                .permit(Trigger.CallDialed, State.Ringing);

        phoneCallConfig.configure(State.Ringing)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.CallConnected, State.Connected);

        phoneCallConfig.configure(State.Connected)
                .onEntry(callStartTimer)
                .onExit(callStopTimer)
                .permit(Trigger.LeftMessage, State.OffHook)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.PlacedOnHold, State.OnHold);

        // ...

        phoneCall = new StateMachine<>(State.OffHook, phoneCallConfig);
	
	}
	
    @Test
    public void testPhoneRings() {
        phoneCall.fire(Trigger.CallDialed);
        assertEquals(State.Ringing, phoneCall.getState());
    }

    @Test(expected=IllegalStateException.class)
    public void testHungupInOffHookState() throws Exception {
        phoneCall.fire(Trigger.HungUp);
        assertEquals(State.Ringing, phoneCall.getState());
    }
    
    private void stopCallTimer() {
        // ...
    }

    private void startCallTimer() {
        // ...
    }

    private enum State {
        Ringing, Connected, OnHold, OffHook

    }
    private enum Trigger {
        CallDialed, CallConnected, PlacedOnHold, LeftMessage, HungUp

    }
}
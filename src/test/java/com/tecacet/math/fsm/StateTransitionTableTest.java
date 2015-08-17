package com.tecacet.math.fsm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

enum ReviewState {
    Developed, RequiresChanges, Modified, Approved, Rejected
}

enum Action {
    Review, Approve, Modify, Reject
}

public class StateTransitionTableTest {

    @Test
    public void testDiagram() throws InvalidTransitionException {
        StateTransitionTable<ReviewState, Action> table = 
            new StateTransitionTable<ReviewState, Action>();
        table.addTransition(ReviewState.Developed, Action.Approve, ReviewState.Approved);
        table.addTransition(ReviewState.Developed, Action.Review, ReviewState.RequiresChanges);
        table.addTransition(ReviewState.Developed, Action.Reject, ReviewState.Rejected);
        table.addTransition(ReviewState.RequiresChanges, Action.Modify, ReviewState.Modified);
        table.addTransition(ReviewState.Modified, Action.Review, ReviewState.RequiresChanges);
        table.addTransition(ReviewState.Modified, Action.Reject, ReviewState.Rejected);
        table.addTransition(ReviewState.Modified, Action.Approve, ReviewState.Approved);
        
        assertEquals(ReviewState.Rejected, table.getNextState(ReviewState.Modified, Action.Reject));
        try {
            table.getNextState(ReviewState.Rejected, Action.Approve);
        } catch (InvalidTransitionException ite) {
            assertEquals("There is no transition from state Rejected on symbol Approve.", ite.getMessage());
        }
        
        System.out.println(table.getDetails());
    }
}

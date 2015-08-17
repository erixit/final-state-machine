package com.tecacet.math.fsm;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

enum Switch {
    ON,
    OFF;
}

public class EnumAlphabetTest {

    @Test
    public void testContains() {
        EnumAlphabet<Switch> alphabet = new EnumAlphabet<Switch>(Switch.class);
        assertTrue(alphabet.contains(Switch.OFF));
        assertTrue(alphabet.contains(Switch.ON));
        Set<Switch> ac = alphabet.getSymbolSet();
        assertEquals(2, ac.size());
    }

}

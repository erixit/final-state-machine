package com.tecacet.math.fsm;

import java.util.EnumSet;

public class EnumAlphabet<E extends Enum<E>> extends Alphabet<E> {
    
    public EnumAlphabet(Class<E> clazz) {
        this.symbols = EnumSet.allOf(clazz);
    }
}

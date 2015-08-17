package com.tecacet.math.fsm;

import java.util.List;

/**
 * A word (or string) is a sequence of symbols in an alphabet.
 *
 * @param <C>
 */
public interface Word<C> {

    /**
     * Returns the symbol value at the specified index.
     * @param index
     * @return
     */
    C  symbolAt(int index);

    /**
     * Returns the length of this character sequence.
     * @return
     */
    int length();
   
    /**
     * Returns a new AlphabetString that is a subsequence of this sequence.
     * @param start
     * @param end
     * @return
     */
    Word<C> substring(int start, int end);
    
    Word<C> substring(int start);
    
    List<C> asList();
}

package com.aout.kata.unittests;

import com.aout.kata.StringCalculator;
import org.junit.Test;

import static junit.framework.Assert.*;


public class StringCalculatorTests {

    private StringCalculator makeCalc() {
        return new StringCalculator();
    }
    private void assertAdding(String numbers, int expected) {
        StringCalculator sc = makeCalc();

        int result = sc.add(numbers);

        assertEquals(expected, result);
    }

    @Test
    public void add_multipleNumbers_returnstheSum(){
        assertAdding("1,2", 3);
    }

    @Test
    public void add_singleNumber_returnsThatNumber(){
        assertAdding("1", 1);
    }



    @Test
    public void add_emptyString_returnsZero(){
        assertAdding("",0);
    }
}

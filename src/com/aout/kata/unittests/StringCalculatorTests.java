package com.aout.kata.unittests;

import com.aout.kata.StringCalculator;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;


public class StringCalculatorTests {

    private StringCalculator makeCalc() {
        return new StringCalculator();
    }

    @Test
    public void add_singleNumber_returnsThatNumber(){
        StringCalculator sc = makeCalc();

        int result = sc.add("1");

        assertEquals(1, result);
    }


    @Test
    public void add_emptyString_returnsZero(){
        StringCalculator sc = makeCalc();

        int result = sc.add("");

        assertEquals(0, result);
    }
}
